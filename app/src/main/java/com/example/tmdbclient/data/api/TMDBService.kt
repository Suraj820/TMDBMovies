package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopulerMovies(
        @Query(
            "api_key"
        )apiKey:String
    ) :Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopulerTvShows(
        @Query(
            "api_key"
        )apiKey:String
    ) :Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopulerArtists(
        @Query(
            "api_key"
        )apiKey:String
    ) :Response<ArtistList>
}