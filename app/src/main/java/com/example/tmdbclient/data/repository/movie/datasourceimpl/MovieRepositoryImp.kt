package com.example.tmdbclient.data.repository.movie.datasourceimpl

import android.util.Log
import com.example.tmdbclient.data.domain.repository.MovieRepository
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource

class MovieRepositoryImp(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {

    override suspend fun getMovies(): List<Movie>? {
       return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovie = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovie)
        movieCacheDataSource.saveMoviesToCache(newListOfMovie)
        return newListOfMovie
    }
    suspend fun getMoviesFromAPI():List<Movie>{
        lateinit var  movieList:List<Movie>
        try {
            val response = movieRemoteDatasource.getMovies()
            val body = response.body()
            if (body!= null){
                movieList = body.movies
            }

        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        return movieList
    }
    suspend fun getMoviesFromDB():List<Movie>{
        lateinit var  movieList:List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (movieList.size > 0){
            return movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }
    suspend fun getMoviesFromCache():List<Movie>{
        lateinit var  movieList:List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (movieList.size > 0){
            return movieList
        }else{
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}