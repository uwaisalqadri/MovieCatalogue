package com.masuwes.core.domain.usecase.movie

import com.masuwes.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovies(sortBy: String, page: Int): Flow<List<Movie>>

//    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie>
//
//    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>>
}