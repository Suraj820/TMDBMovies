package com.example.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtitesDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvshowDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun providerMovieDataBase(context: Context):TMDBDatabase{
        return Room.databaseBuilder(context,TMDBDatabase::class.java,"tmdbclient")
            .build()
    }

    @Singleton
    @Provides
    fun provoiderMovieDao(tmdbDatabase: TMDBDatabase):MovieDAO{
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provoiderTvShowDao(tmdbDatabase: TMDBDatabase):TvshowDAO{
        return tmdbDatabase.tvShowDao()
    }

    @Singleton
    @Provides
    fun provoiderArtiestDao(tmdbDatabase: TMDBDatabase):ArtitesDAO{
        return tmdbDatabase.artistDao()
    }
}