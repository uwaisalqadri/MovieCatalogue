package com.masuwes.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.utils.Constants
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
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

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var response: List<Movie>

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovies() {
        runBlocking {
            Mockito.`when`(useCase.getMovies(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1))
        }.thenReturn(Single.just(response))
        viewModel.getMovies()

        viewModel.postMovieData.observeForever(observer)
        Mockito.verify(observer).onChanged(response)

    }

}















