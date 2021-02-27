package com.masuwes.core.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.utils.Resource
import io.reactivex.Single

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<Movie>> =
        movieRepository.getMovies(api_key, language, sort_by, page)

    override fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie> =
        movieRepository.getMoviesAsPaged()

    override fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>> =
        movieRepository.getMoviePage()
}