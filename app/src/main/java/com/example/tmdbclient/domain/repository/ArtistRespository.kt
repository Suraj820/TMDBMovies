package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistRespository {
    suspend fun getArtites():List<Artist>?
    suspend fun updateArtites():List<Artist>?
}