package com.masuwes.moviecatalogue.domain.usecase

import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.repository.MovieRepository
import io.reactivex.Single

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getMovies(
        api_key: String,
        language: String,
        page: Int
    ): Single<List<Movie>> =
        movieRepository.getMovies(api_key, language, page)
}