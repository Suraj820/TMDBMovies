package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.repository.artist.datasourceimpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private  val apiKey :String) {

    @Singleton
    @Provides
    fun providerMovieDataSource(tmdbService: TMDBService):MovieRemoteDatasource{
        return MovieRemoteDataSourceImpl(tmdbService,apiKey)
    }


    @Singleton
    @Provides
    fun providerArtistDataSource(tmdbService: TMDBService):ArtistRemoteDataSourceImpl{
        return ArtistRemoteDataSourceImpl(tmdbService,apiKey)
    }

    @Singleton
    @Provides
    fun providerTvShowDataSource(tmdbService: TMDBService):TvShowRemoteDataSourceImpl{
        return TvShowRemoteDataSourceImpl(tmdbService,apiKey)
    }




}