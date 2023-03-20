package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRespository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }
    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository):UpdateMovieUseCase{
        return UpdateMovieUseCase(movieRepository)
    }

    @Provides
    fun provideTvShowUseCase(tvShowRepository: TvShowRepository):GetTvShowsUseCase{
        return GetTvShowsUseCase(tvShowRepository)
    }
    @Provides
    fun provideTvShowUseUseCase(tvShowRepository: TvShowRepository):UpdateTvShowsUseCase{
        return UpdateTvShowsUseCase(tvShowRepository)
    }


    @Provides
    fun provideGetArtistUseCase(artistRespository: ArtistRespository):GetArtitesUseCase{
        return GetArtitesUseCase(artistRespository)
    }
    @Provides
    fun provideUpdateArtistUseCase(artistRespository: ArtistRespository):UpdateArtitesUseCase{
        return UpdateArtitesUseCase(artistRespository)
    }
}