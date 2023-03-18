package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie

interface ArtistCacheDatasource {
    suspend fun getArtistFromCache():List<Artist>
    suspend fun saveArtistToCache(artist :List<Artist>)
}