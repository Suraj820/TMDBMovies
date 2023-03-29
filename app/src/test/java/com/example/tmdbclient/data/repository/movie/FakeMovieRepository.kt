package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository :MovieRepository {
    private val movies = mutableListOf<Movie>()
    init {
        movies.add(Movie(1,"overview1","Path1","date1","title1"))
        movies.add(Movie(2,"overview2","Path2","date2","title2"))
        movies.add(Movie(3,"overview3","Path3","date3","title3"))
    }
    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.add(Movie(4,"overview4","Path4","date4","title4"))
        movies.add(Movie(5,"overview5","Path5","date5","title5"))
        return movies

    }
}