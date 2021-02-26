package com.masuwes.core.domain.usecase.movie

import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.repository.MovieRepository
import io.reactivex.Single

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<Movie>> =
        movieRepository.getMovies(api_key, language, sort_by, page)
}