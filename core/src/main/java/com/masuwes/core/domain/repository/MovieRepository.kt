package com.masuwes.core.domain.repository

import com.masuwes.core.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ) : Single<List<Movie>>

//    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovieEntity>
//
//    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovieEntity>>>

}