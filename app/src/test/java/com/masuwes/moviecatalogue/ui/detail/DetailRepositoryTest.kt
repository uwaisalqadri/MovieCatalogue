package com.masuwes.moviecatalogue.ui.detail

import com.masuwes.moviecatalogue.data.mapper.DetailMovieMapper
import com.masuwes.moviecatalogue.data.mapper.DetailTvShowMapper
import com.masuwes.moviecatalogue.data.model.detail.DetailMovieItem
import com.masuwes.moviecatalogue.data.model.detail.DetailTvShowItem
import com.masuwes.moviecatalogue.data.remote.ApiService
import com.masuwes.moviecatalogue.data.repository.DetailRepositoryImpl
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.utils.Constants
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var repositoryImpl: DetailRepositoryImpl

    private var moviesMapper = DetailMovieMapper()
    private var tvShowMapper = DetailTvShowMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositoryImpl = DetailRepositoryImpl(apiService, moviesMapper, tvShowMapper)
    }

    @Test
    fun getDetailMovies() {
        Mockito.`when`(apiService.getMovieDetail(330457, Constants.LANG, Constants.SORT_BY))
                .thenReturn(Single.just(
                        DetailMovieItem(
                                false,
                                "/vZiqhw6oFoMlHSneIdVip9rRou2.jpg",
                                1,
                                "en",
                                "Frozen II",
                                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                                400.100,
                                "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
                                "2019-11-20",
                                "Frozen II",
                                false,
                                1.9,
                                100
                        )
                ))

        repositoryImpl.getDetailMovie(330457, Constants.LANG, Constants.SORT_BY)
                .test()
                .assertComplete()
    }

    @Test
    fun getDetailTvShows() {
        Mockito.`when`(apiService.getTvShowDetail(82856, Constants.LANG, Constants.SORT_BY))
                .thenReturn(Single.just(
                        DetailTvShowItem(
                                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                                "2020-01-12",
                                1,
                                "tvShow",
                                "en",
                                "Acara TV",
                                "gini bang gini",
                                100.100,
                                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                                9.0,
                                100
                        )
                ))

        repositoryImpl.getDetailTvShow(82856, Constants.LANG, Constants.SORT_BY)
                .test()
                .assertComplete()
    }


}


















