package com.masuwes.core.domain.usecase.movie

import com.masuwes.core.data.mapper.response.map
import com.masuwes.core.domain.base.execute
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepository): MovieUseCase {

    override fun getMovies(sortBy: String, page: Int): Flow<List<Movie>> {
        return execute {
            movieRepository.getMovies(sortBy, page).map()
        }
    }
}