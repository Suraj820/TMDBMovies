package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.getOrAwaitValue
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMovieUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUsecase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUsecase = UpdateMovieUseCase(fakeMovieRepository)
         viewModel = MovieViewModel(getMoviesUsecase,updateMoviesUsecase)

    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1,"overview1","Path1","date1","title1"))
        movies.add(Movie(2,"overview2","Path2","date2","title2"))
        movies.add(Movie(3,"overview3","Path3","date3","title3"))
        val currentList = viewModel.getMovies().getOrAwaitValue()
       assertThat(currentList).isEqualTo(movies)

    }

    @Test
    fun updateMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(4,"overview4","Path4","date4","title4"))
        movies.add(Movie(5,"overview5","Path5","date5","title5"))

        val updateList = viewModel.updateMovies().getOrAwaitValue()
        assertThat(updateList).isEqualTo(movies)

    }
}