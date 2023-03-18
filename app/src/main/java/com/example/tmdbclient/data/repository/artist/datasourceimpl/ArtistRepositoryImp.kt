package com.example.tmdbclient.data.repository.artist.datasourceimpl

import android.util.Log
import com.example.tmdbclient.data.domain.repository.ArtitiesRespository
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDatasource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource

class ArtistRepositoryImp (
    private val artistRemoteDatasource: ArtistRemoteDatasource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDatasource: ArtistCacheDatasource
    ):ArtitiesRespository{
    override suspend fun getArtites(): List<Artist>? {
        TODO("Not yet implemented")
    }

    override suspend fun updateArtites(): List<Artist>? {
        TODO("Not yet implemented")
    }

    suspend fun getArtistFromAPI():List<Artist>{
        lateinit var  artistList:List<Artist>
        try {
            val response = artistRemoteDatasource.getArtist()
            val body = response.body()
            if (body!= null){
                artistList = body.artists
            }

        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        return artistList
    }
    suspend fun  getArtistFromDB():List<Artist>{
        lateinit var  artistList:List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistFromDB()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
        }
        return artistList
    }
    suspend fun getMoviesFromCache():List<Artist>{
        lateinit var  artistList:List<Artist>
        try {
            artistList = artistCacheDatasource.getArtistFromCache()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistFromDB()
            artistCacheDatasource.saveArtistToCache(artistList)
        }
        return artistList
    }


}