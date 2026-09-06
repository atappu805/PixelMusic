package com.unshoo.pixelmusic.di

import android.content.Context
import com.unshoo.pixelmusic.data.preferences.UserPreferencesRepository
import com.unshoo.pixelmusic.data.remote.youtube.DatastoreRepository
import com.unshoo.pixelmusic.data.remote.youtube.SongRepository
import com.unshoo.pixelmusic.data.remote.youtube.YoutubePlaylistDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object YoutubeModule {

    @Provides
    @Singleton
    fun provideExoCache(
        @ApplicationContext context: Context,
        userPreferencesRepository: UserPreferencesRepository
    ): com.unshoo.pixelmusic.data.remote.youtube.ExoCache {
        return com.unshoo.pixelmusic.data.remote.youtube.ExoCache(context, userPreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideDatastoreRepository(@ApplicationContext context: Context): DatastoreRepository {
        return DatastoreRepository(context)
    }

    @Provides
    @Singleton
    fun provideSongRepository(): SongRepository {
        return SongRepository()
    }

    @Provides
    @Singleton
    fun provideYoutubePlaylistDataSource(): YoutubePlaylistDataSource {
        return YoutubePlaylistDataSource()
    }
}
