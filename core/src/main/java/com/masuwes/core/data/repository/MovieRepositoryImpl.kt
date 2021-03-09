package com.masuwes.core.data.repository

import com.masuwes.core.data.mapper.response.MovieMapper
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.utils.AppExecutors
import io.reactivex.Single

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

//    override fun getMoviesAsPaged(): DataSource.Factory<Int, DetailMovieEntity> = moviesDao.getFavMoviePagination()
//
//    override fun getMoviePage(): LiveData<Resource<PagedList<DetailMovieEntity>>> {
//        return object :
//                NetworkBoundResource<PagedList<DetailMovieEntity>, List<DetailMovieEntity>>(
//                        executors
//                ) {
//            override fun loadFromDB(): LiveData<PagedList<DetailMovieEntity>> {
//                return LivePagedListBuilder(
//                        getMoviesAsPaged(), 20
//                ).build()
//            }
//
//            override fun shouldFetch(data: PagedList<DetailMovieEntity>?): Boolean {
//                return false
//            }
//
//            override fun createCall(): LiveData<ApiResponse<List<DetailMovieEntity>>>? {
//                return null
//            }
//
//            override fun saveCallResult(data: List<DetailMovieEntity>) {
//
//            }
//        }.asLiveData()
//    }
}




















