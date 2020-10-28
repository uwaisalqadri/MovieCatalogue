package com.masuwes.moviecatalogue.ui.movie

import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.utils.Constants
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var response: List<Movie>

    @Mock
    private lateinit var useCase: MovieUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovies() {
        val movieResponse = response
        runBlocking {
            Mockito.`when`(useCase.getMovies(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1))
        }.thenReturn(Single.just(movieResponse))
        viewModel.getMovies()
    }
}















