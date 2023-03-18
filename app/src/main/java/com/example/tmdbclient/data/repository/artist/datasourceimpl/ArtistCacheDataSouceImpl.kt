package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDatasource

class ArtistCacheDataSouceImpl:ArtistCacheDatasource {
    private var artistList =ArrayList<Artist>()
    override suspend fun getArtistFromCache(): List<Artist> {
       return artistList
    }

    override suspend fun saveArtistToCache(artist: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artist)
    }
}