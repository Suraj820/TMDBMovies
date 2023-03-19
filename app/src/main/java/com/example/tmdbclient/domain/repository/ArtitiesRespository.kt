package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artist.Artist

interface ArtitiesRespository {
    suspend fun getArtites():List<Artist>?
    suspend fun updateArtites():List<Artist>?
}