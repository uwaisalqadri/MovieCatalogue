package com.masuwes.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.utils.Resource
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ) : Single<List<Movie>>

    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie>

    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>>
}