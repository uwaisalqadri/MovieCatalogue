package com.masuwes.core.data.repository

import com.masuwes.core.data.model.detail.entity.DetailMovieEntity
import com.masuwes.core.data.model.detail.entity.DetailTvShowEntity
import com.masuwes.core.data.model.detail.response.DetailMovieItem
import com.masuwes.core.data.model.detail.response.DetailTvShowItem
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.local.TvShowsDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.DetailRepository
import java.util.concurrent.Executor

class DetailDataStore(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val tvShowsDao: TvShowsDao
) : DetailRepository {

    override suspend fun getDetailMovie(movieId: Int): DetailMovieItem {
        return apiService.getMovieDetail(movieId = movieId)
    }

    override suspend fun getFavoriteMovieById(movieId: Int): List<DetailMovieEntity> {
        return moviesDao.getFavoriteMovieById(movieId)
    }

    override suspend fun insertFavoriteMovie(movie: DetailMovieEntity) {
        moviesDao.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        moviesDao.deleteFavoriteMovie(movieId)
    }

    override suspend fun getDetailTvShow(tvId: Int): DetailTvShowItem {
        return apiService.getTvShowDetail(tvId = tvId)
    }

    override suspend fun getFavoriteTvById(tvId: Int): List<DetailTvShowEntity> {
        return tvShowsDao.getFavoriteTvById(tvId)
    }

    override suspend fun insertFavoriteTv(tvShow: DetailTvShowEntity) {
        tvShowsDao.insertFavoriteTv(tvShow)
    }

    override suspend fun deleteFavoriteTv(tvId: Int) {
        tvShowsDao.deleteFavoriteTv(tvId)
    }

}