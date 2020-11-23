package com.masuwes.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.utils.Constants
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var response: Movie

    @Mock
    private lateinit var useCase: MovieUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovies() {
        val movieResponse = listOf(response)
        runBlocking {
            Mockito.`when`(
                    useCase.getMovies(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1)
            )
        }.thenReturn(Single.just(movieResponse))
        viewModel.getMovies(1)
    }

}



















