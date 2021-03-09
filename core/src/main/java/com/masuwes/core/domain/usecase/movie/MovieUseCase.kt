package com.masuwes.core.domain.usecase.movie

import com.masuwes.core.domain.model.Movie
import io.reactivex.Single

interface MovieUseCase {

    fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<Movie>>

//    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie>
//
//    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>>
}