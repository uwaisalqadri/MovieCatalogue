package com.masuwes.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masuwes.core.data.mapper.entity.DetailMovieEntityMapper
import com.masuwes.core.data.mapper.response.MovieMapper
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.utils.ApiResponse
import com.masuwes.core.utils.AppExecutors
import com.masuwes.core.utils.NetworkBoundResource
import com.masuwes.core.utils.Resource
import io.reactivex.Single

class MovieRepositoryImpl(
        private val apiService: ApiService,
        private val itemMovieMapper: MovieMapper,
        private val itemDetailMovieEntityMapper: DetailMovieEntityMapper,
        private val moviesDao: MoviesDao,
        val executors: AppExecutors
) : MovieRepository {
    override fun getMovies(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<Movie>> {
        return apiService.getMovies(api_key, language, sort_by, page
        ).map {
            itemMovieMapper.mapToListDomain(it.results)
        }
    }

    override fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie> =
            moviesDao.getFavMoviePagination().map {
                itemDetailMovieEntityMapper.mapToDomain(it)
            }

    override fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>> {
        return object :
                NetworkBoundResource<PagedList<DetailMovie>, List<DetailMovie>>(
                        executors
                ) {
            override fun loadFromDB(): LiveData<PagedList<DetailMovie>> {
                return LivePagedListBuilder(
                        getMoviesAsPaged(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<DetailMovie>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<DetailMovie>>>? {
                return null
            }

            override fun saveCallResult(data: List<DetailMovie>) {

            }
        }.asLiveData()
    }
}




















