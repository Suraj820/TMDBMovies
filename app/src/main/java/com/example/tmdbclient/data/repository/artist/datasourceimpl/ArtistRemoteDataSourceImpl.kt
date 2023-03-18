package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey:String):ArtistRemoteDatasource {
    override suspend fun getArtist(): Response<ArtistList> = tmdbService.getPopulerArtists(apiKey)
}