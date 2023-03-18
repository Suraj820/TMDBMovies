package com.example.tmdbclient.data.domain.usecase

import com.example.tmdbclient.data.domain.repository.ArtitiesRespository
import com.example.tmdbclient.data.model.artist.Artist

class GetArtitesUseCase(private val artitiesRespository: ArtitiesRespository) {
    suspend fun execute():List<Artist>? = artitiesRespository.getArtites()

}