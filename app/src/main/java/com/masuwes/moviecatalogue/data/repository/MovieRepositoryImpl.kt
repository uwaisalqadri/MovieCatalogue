package com.masuwes.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masuwes.moviecatalogue.data.local.dao.MoviesDao
import com.masuwes.moviecatalogue.data.mapper.MovieMapper
import com.masuwes.moviecatalogue.data.remote.ApiService
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.repository.MovieRepository
import com.masuwes.moviecatalogue.utils.room.ApiResponse
import com.masuwes.moviecatalogue.utils.room.AppExecutors
import com.masuwes.moviecatalogue.utils.room.NetworkBoundResource
import com.masuwes.moviecatalogue.utils.room.Resource
import io.reactivex.Single
import java.util.concurrent.Executor

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val itemMovieMapper: MovieMapper,
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

    fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovie> = moviesDao.getFavMoviePagination()

    fun getMoviePage(): LiveData<Resource<PagedList<DetailMovie>>> {
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




















