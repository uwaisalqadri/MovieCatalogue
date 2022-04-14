package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.detail.entity.DetailMovieEntity
import com.masuwes.core.data.model.movie.MovieItem

interface MovieRepository {

    suspend fun getMovies(sortBy: String, page: Int): List<MovieItem>

    suspend fun getFavoriteMovie(): List<DetailMovieEntity>
//
//    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovieItem>>>
}