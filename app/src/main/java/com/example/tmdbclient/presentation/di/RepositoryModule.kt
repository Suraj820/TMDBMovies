package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDatasource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import com.example.tmdbclient.data.repository.artist.datasourceimpl.ArtistRepositoryImp
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieRepositoryImp
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDatasource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRepositoryImp
import com.example.tmdbclient.domain.repository.ArtitiesRespository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDatasource: MovieRemoteDatasource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ) : MovieRepository{
        return MovieRepositoryImp(
            movieRemoteDatasource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistRemoteDatasource: ArtistRemoteDatasource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDatasource
    ) : ArtitiesRespository{
        return ArtistRepositoryImp(
            artistRemoteDatasource,
            artistLocalDataSource,
            artistCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDatasource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowsCacheDataSource: TvShowsCacheDataSource
    ) : TvShowRepository{
        return TvShowRepositoryImp(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowsCacheDataSource
        )
    }
}