package com.masuwes.core.domain.usecase.movie

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getMovies(sortBy: String, page: Int): Flow<List<Movie>>

    suspend fun getFavoriteMovie(): Flow<List<DetailMovie>>
}