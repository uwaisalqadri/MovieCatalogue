package com.masuwes.core.data.repository

import com.masuwes.core.data.model.detail.entity.DetailMovieEntity
import com.masuwes.core.data.model.movie.MovieItem
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.MovieRepository

class MovieDataStore(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
) : MovieRepository {

    override suspend fun getMovies(sortBy: String, page: Int): List<MovieItem> {
        return apiService.getMovies(sortBy = sortBy, page = page).data
    }

    override suspend fun getFavoriteMovie(): List<DetailMovieEntity> {
        return moviesDao.getFavoriteMovie()
    }

}