package com.masuwes.moviecatalogue.domain.repository

import com.masuwes.moviecatalogue.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(
        api_key: String,
        language: String,
        page: Int
    ) : Single<List<Movie>>
}