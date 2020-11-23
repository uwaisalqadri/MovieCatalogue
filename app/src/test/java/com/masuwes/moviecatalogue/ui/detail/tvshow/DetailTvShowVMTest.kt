package com.masuwes.moviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masuwes.moviecatalogue.data.local.dao.MoviesDao
import com.masuwes.moviecatalogue.data.local.dao.TvShowsDao
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieVM
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
class DetailTvShowVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailTvShowVM

    @Mock
    private lateinit var response: DetailTvShow

    @Mock
    private lateinit var useCase: DetailUseCase

    @Mock
    private lateinit var tvShowsDao: TvShowsDao

    @Mock
    private lateinit var executor: Executor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailTvShowVM(useCase, tvShowsDao, executor)
    }

    @Test
    fun getDetailMovies() {
        val tvShowResponse = response
        runBlocking {
            Mockito.`when`(useCase.getDetailTvShow(475430, Constants.API_KEY, Constants.LANG))
        }.thenReturn(Single.just(tvShowResponse))
        viewModel.getDetailTvShow(475430)
    }
}