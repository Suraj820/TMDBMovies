package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDatasource {
    suspend fun getArtist(): Response<ArtistList>
}