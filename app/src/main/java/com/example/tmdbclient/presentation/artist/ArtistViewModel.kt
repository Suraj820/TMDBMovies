package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtitesUseCase

class ArtistViewModel(
    private val getArtistUseCase: GetMoviesUseCase,
    private val updateArtitesUseCase: UpdateArtitesUseCase
): ViewModel() {
    fun getArtist() = liveData {
        val artistList = getArtistUseCase.execute()
        emit(artistList)
    }
    fun updateArtist() = liveData {
        val artistList = updateArtitesUseCase.execute()
        emit(artistList)
    }
}