package com.unshoo.pixelmusic.presentation.viewmodel

import android.app.Activity
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.unshoo.pixelmusic.R
import com.unshoo.pixelmusic.data.model.Song
import com.unshoo.pixelmusic.data.preferences.PlaylistPreferencesRepository
import com.unshoo.pixelmusic.data.repository.MusicRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.first
import kotlin.math.absoluteValue
import unshoo.ianshulyadav.pixelmusic.innertube.YouTube

@ViewModelScoped
class SongRemovalStateHolder @Inject constructor(
    private val musicRepository: MusicRepository,
    private val metadataEditStateHolder: MetadataEditStateHolder,
    private val playlistPreferencesRepository: PlaylistPreferencesRepository,
    private val libraryStateHolder: LibraryStateHolder
) {
    suspend fun showDeleteConfirmation(activity: Activity, song: Song): Boolean {
        return withContext(Dispatchers.Main) {
            try {
                if (activity.isFinishing || activity.isDestroyed) {
                    return@withContext false
                }

                val userChoice = CompletableDeferred<Boolean>()
                val dialog = MaterialAlertDialogBuilder(activity)
                    .setTitle(activity.getString(R.string.dialog_delete_song_title))
                    .setMessage(
                        activity.getString(
                            R.string.dialog_delete_song_message,
                            song.title,
                            song.displayArtist
                        )
                    )
                    .setPositiveButton(activity.getString(R.string.delete_action)) { _, _ ->
                        userChoice.complete(true)
                    }
                    .setNegativeButton(activity.getString(R.string.cancel)) { _, _ ->
                        userChoice.complete(false)
                    }
                    .setOnCancelListener {
                        userChoice.complete(false)
                    }
                    .setCancelable(true)
                    .create()

                dialog.show()
                userChoice.await()
            } catch (_: Exception) {
                false
            }
        }
    }

    suspend fun deleteSongFile(song: Song): Boolean {
        return metadataEditStateHolder.deleteSong(song)
    }

    suspend fun removeSongFromLibrary(song: Song) {
        libraryStateHolder.removeSong(song.id)
        
        // 1. Identify which playlists currently have this song BEFORE we delete it locally
        val currentPlaylists = playlistPreferencesRepository.userPlaylistsFlow.first()
        val playlistsContainingSong = currentPlaylists.filter { it.songIds.contains(song.id) }

        // 2. Remove from local database
        val localId = song.id.toLongOrNull()
        if (localId != null) {
            musicRepository.deleteById(localId)
        } else if (song.id.startsWith("youtube_") || song.youtubeId != null) {
            val yId = song.youtubeId ?: song.id.removePrefix("youtube_")
            val unifiedId = -(15_000_000_000_000L + yId.hashCode().toLong().absoluteValue)
            musicRepository.deleteById(unifiedId)
        }
        
        // 3. Remove from local playlist records
        playlistPreferencesRepository.removeSongFromAllPlaylists(song.id)

        // 4. Extract proper Video ID (Handling Local Room IDs correctly)
        val videoId = song.youtubeId 
            ?: if (song.contentUriString.startsWith("youtube://")) song.contentUriString.substringAfter("youtube://")
            else if (song.id.startsWith("youtube_")) song.id.removePrefix("youtube_") 
            else null

        // 5. Sync deletions to remote YouTube playlists so it doesn't come back on refresh!
        if (!videoId.isNullOrBlank()) {
            playlistsContainingSong.filter { it.source == "YOUTUBE" }.forEach { playlist ->
                try {
                    withContext(Dispatchers.IO) {
                        val setVideoIds = YouTube.playlistEntrySetVideoIds(playlist.id, videoId).getOrNull()
                        setVideoIds?.forEach { setVideoId ->
                            YouTube.removeFromPlaylist(playlist.id, videoId, setVideoId)
                        }
                    }
                } catch (e: Exception) {
                    Log.e("SongRemoval", "Failed to sync song removal to YouTube playlist ${playlist.id}", e)
                }
            }
        }
    }
}
