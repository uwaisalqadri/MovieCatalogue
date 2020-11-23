package com.masuwes.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masuwes.moviecatalogue.data.local.dao.MoviesDao
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.usecase.detail.DetailUseCase
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
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class DetailMovieVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailMovieVM

    @Mock
    private lateinit var response: DetailMovie

    @Mock
    private lateinit var useCase: DetailUseCase

    @Mock
    private lateinit var moviesDao: MoviesDao

    @Mock
    private lateinit var executor: Executor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailMovieVM(useCase, moviesDao, executor)
    }

    @Test
    fun getDetailMovies() {
        val movieResponse = response
        runBlocking {
            Mockito.`when`(useCase.getDetailMovie(475430, Constants.API_KEY, Constants.LANG))
        }.thenReturn(Single.just(movieResponse))
        viewModel.getDetailMovie(475430)
    }
}


















