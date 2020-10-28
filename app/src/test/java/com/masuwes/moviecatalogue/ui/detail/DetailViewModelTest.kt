package com.masuwes.moviecatalogue.ui.detail

import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.movie.DetailUseCase
import com.masuwes.moviecatalogue.utils.Constants
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest  {

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var responseMovie: DetailMovie

    @Mock
    private lateinit var responseShow: DetailTvShow

    @Mock
    private lateinit var useCase: DetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun getDetailMovies() {
        val movieResponse = responseMovie
        runBlocking {
            Mockito.`when`(useCase.getDetailMovie("330457", Constants.API_KEY, Constants.LANG))
        }.thenReturn(Single.just(movieResponse))
        viewModel.getDetailMovie("330457")
    }

    @Test
    fun getDetailShows() {
        val showResponse = responseShow
        runBlocking {
            Mockito.`when`(useCase.getDetailTvShow("60735", Constants.API_KEY, Constants.LANG))
        }.thenReturn(Single.just(showResponse))
        viewModel.getDetailTvShow("60735")
    }
}