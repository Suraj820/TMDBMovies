package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.db.ArtitesDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TvshowDAO
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowsLocalDataSourceIml
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun providerMovieLocalDataSource(movieDAO: MovieDAO):MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDAO)
    }

    @Singleton
    @Provides
    fun providerArtistLocalDataSource(artistDAO: ArtitesDAO): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDAO)
    }

    @Singleton
    @Provides
    fun providerTvShowLocalDataSource(tvshowDAO: TvshowDAO): TvShowLocalDataSource {
        return TvShowsLocalDataSourceIml(tvshowDAO)
    }

}