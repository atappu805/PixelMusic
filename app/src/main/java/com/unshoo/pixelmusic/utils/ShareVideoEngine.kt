package com.unshoo.pixelmusic.utils

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.transformer.Composition
import androidx.media3.transformer.EditedMediaItem
import androidx.media3.transformer.EditedMediaItemSequence
import androidx.media3.transformer.ExportException
import androidx.media3.transformer.ExportResult
import androidx.media3.transformer.Transformer
import com.google.common.collect.ImmutableList // FIXED: Added Guava ImmutableList import
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.resume

object ShareVideoEngine {

    suspend fun createInstagramShareVideo(
        context: Context,
        imagePath: String,
        audioPath: String,
        outputPath: String
    ): Boolean = withContext(Dispatchers.Main) {
        val outputFile = File(outputPath)
        if (outputFile.exists()) outputFile.delete()

        return@withContext suspendCancellableCoroutine { continuation ->
            try {
                // 1. Prepare the static image track (15 seconds at 30fps)
                val imageMediaItem = MediaItem.fromUri(Uri.parse("file://$imagePath"))
                val editedImage = EditedMediaItem.Builder(imageMediaItem)
                    .setDurationUs(15_000_000L) // 15 seconds
                    .setFrameRate(30)
                    .build()
                
                // FIXED: Wrap the EditedMediaItem in an ImmutableList
                val imageSequence = EditedMediaItemSequence(ImmutableList.of(editedImage))

                // 2. Prepare the audio track (Trimmed from 30s to 45s for the hook)
                val audioMediaItem = MediaItem.Builder()
                    .setUri(Uri.parse("file://$audioPath"))
                    .setClippingConfiguration(
                        MediaItem.ClippingConfiguration.Builder()
                            .setStartPositionMs(30_000L)
                            .setEndPositionMs(45_000L)
                            .build()
                    )
                    .build()
                val editedAudio = EditedMediaItem.Builder(audioMediaItem).build()
                
                // FIXED: Wrap the EditedMediaItem in an ImmutableList
                val audioSequence = EditedMediaItemSequence(ImmutableList.of(editedAudio))

                // 3. Combine them into a single hardware-accelerated composition
                // mutableListOf is still valid here because Composition.Builder accepts standard Lists
                val composition = Composition.Builder(mutableListOf(imageSequence, audioSequence)).build()

                // 4. Configure Transformer for MP4 encoding
                val transformer = Transformer.Builder(context)
                    .addListener(object : Transformer.Listener {
                        override fun onCompleted(composition: Composition, exportResult: ExportResult) {
                            if (continuation.isActive) continuation.resume(true)
                        }

                        override fun onError(
                            composition: Composition,
                            exportResult: ExportResult,
                            exportException: ExportException
                        ) {
                            exportException.printStackTrace()
                            if (continuation.isActive) continuation.resume(false)
                        }
                    })
                    .build()

                // 5. Start the render
                transformer.start(composition, outputPath)

                // Cancel if the coroutine dies
                continuation.invokeOnCancellation {
                    transformer.cancel()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (continuation.isActive) continuation.resume(false)
            }
        }
    }
}
