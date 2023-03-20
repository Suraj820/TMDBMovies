package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.domain.repository.ArtistRespository
import com.example.tmdbclient.data.model.artist.Artist

class UpdateArtitesUseCase(private val artitiesRespository: ArtistRespository) {
    suspend fun execute():List<Artist>? = artitiesRespository.updateArtites()

}