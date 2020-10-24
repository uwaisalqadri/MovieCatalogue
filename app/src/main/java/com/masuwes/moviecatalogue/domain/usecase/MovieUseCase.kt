package com.masuwes.moviecatalogue.domain.usecase

import com.masuwes.moviecatalogue.domain.model.Movie
import io.reactivex.Single

interface MovieUseCase {

    fun getMovies(
        api_key: String,
        language: String,
        page: Int
    ) : Single<List<Movie>>
}