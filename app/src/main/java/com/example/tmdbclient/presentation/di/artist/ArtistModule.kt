package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.GetArtitesUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtitesUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtitesUseCase: GetArtitesUseCase,
        updateArtitesUseCase: UpdateArtitesUseCase
    ):ArtistViewModelFactory{
        return ArtistViewModelFactory(getArtitesUseCase,updateArtitesUseCase)
    }
}