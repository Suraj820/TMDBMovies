package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import android.util.Log
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDatasource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsCacheDataSource

class TvShowRepositoryImp (
    private val tvShowRemoteDataSource: TvShowRemoteDatasource,
    private val tvShowsLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource:  TvShowsCacheDataSource
    ): TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
       return getTvShowFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfMovie = getTvShowFromAPI()
        tvShowsLocalDataSource.clearAll()
        tvShowsLocalDataSource.saveTvShowsToDB(newListOfMovie)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfMovie)
        return newListOfMovie
    }

    suspend fun getTvShowFromAPI():List<TvShow>{
        lateinit var  tvShowList:List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body!= null){
                tvShowList = body.tvShows
            }

        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        return tvShowList
    }
    suspend fun getTvShowFromDB():List<TvShow>{
        lateinit var  tvShowList:List<TvShow>
        try {
            tvShowList = tvShowsLocalDataSource.getTvShowsFromDB()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (tvShowList.size > 0){
            return tvShowList
        }else{
            tvShowList = getTvShowFromAPI()
            tvShowsLocalDataSource.saveTvShowsToDB(tvShowList)
        }
        return tvShowList
    }
    suspend fun getTvShowFromCache():List<TvShow>{
        lateinit var  tvShowList:List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        }catch (exception : Exception){
            Log.e("TAG", "getMoviesFromAPI: "+exception.message )
        }
        if (tvShowList.size > 0){
            return tvShowList
        }else{
            tvShowList = getTvShowFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}