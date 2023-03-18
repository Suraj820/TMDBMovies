package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.db.ArtitesDAO
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDAO: ArtitesDAO):ArtistLocalDataSource {
    override suspend fun getArtistFromDB(): List<Artist> {
       return artistDAO.getArtist()
    }

    override suspend fun saveArtistToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDAO.saveArtist(artists)
        }
    }

    override suspend fun clearAll() {
        artistDAO.deleteAllArtist()
    }
}