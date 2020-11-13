package com.masuwes.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.utils.Constants
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var response: List<TvShow>

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun getTvShows() {
        val showResponse = response
        runBlocking {
            Mockito.`when`(useCase.getTvShows(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1))
        }.thenReturn(Single.just(showResponse))
        viewModel.getTvShows()

        viewModel.postTvShowData.observeForever(observer)
        Mockito.verify(observer).onChanged(response)
    }
}