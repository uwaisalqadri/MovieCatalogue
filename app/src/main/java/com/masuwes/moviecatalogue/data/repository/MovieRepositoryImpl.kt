package com.masuwes.moviecatalogue.data.repository

import com.masuwes.moviecatalogue.data.mapper.MovieMapper
import com.masuwes.moviecatalogue.data.service.ApiService
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val itemMovieMapper: MovieMapper
) : MovieRepository {
    override fun getMovies(
        api_key: String,
        language: String,
        page: Int
    ): Single<List<Movie>> {
        return apiService.getMovies(api_key, language, page
        ).map {
            itemMovieMapper.mapToListDomain(it.results)
        }
    }
}