package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.response.movie.MovieItem

interface MovieRepository {

    suspend fun getMovies(sortBy: String, page: Int): List<MovieItem>

//    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovieItem>
//
//    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovieItem>>>
}