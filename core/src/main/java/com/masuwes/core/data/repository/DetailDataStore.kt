package com.masuwes.core.data.repository

import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.local.TvShowsDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.DetailRepository
import timber.log.Timber
import java.util.concurrent.Executor

class DetailDataStore(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val tvShowsDao: TvShowsDao,
    private val executor: Executor,
) : DetailRepository {

    override suspend fun getDetailMovie(movieId: Int): DetailMovieItem {
        return apiService.getMovieDetail(movieId = movieId)
    }

    override fun getFavoriteMovieById(idMovie: Int): List<DetailMovieItem> {
        return emptyList()
    }

    override suspend fun insertFavoriteMovie(movies: DetailMovieItem) {

    }

    override suspend fun deleteFavoriteMovie(idMovie: Int) {

    }

    override suspend fun getDetailTvShow(tvId: Int): DetailTvShowItem {
        return apiService.getTvShowDetail(tvId = tvId)
    }

    override fun getFavoriteTvById(idTv: Int): List<DetailTvShowItem> {
        return emptyList()
    }

    override suspend fun insertFavoriteTv(tvShow: DetailTvShowItem) {
    }

    override suspend fun deleteFavoriteTv(idTv: Int) {
    }

}