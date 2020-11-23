package com.masuwes.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowUseCase
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
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var response: TvShow

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun getTvShows() {
        val tvShowResponse = listOf(response)
        runBlocking {
            Mockito.`when`(
                    useCase.getTvShows(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1)
            )
        }.thenReturn(Single.just(tvShowResponse))
        viewModel.getTvShows(1)
    }
}























