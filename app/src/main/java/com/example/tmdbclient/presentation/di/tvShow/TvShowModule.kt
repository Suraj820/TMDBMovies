package com.example.tmdbclient.presentation.di.tvShow

import com.example.tmdbclient.domain.usecase.GetArtitesUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtitesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.example.tmdbclient.presentation.TvShow.TvShowViewModelFactory
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ):TvShowViewModelFactory{
        return TvShowViewModelFactory(getTvShowsUseCase,updateTvShowsUseCase)
    }
}