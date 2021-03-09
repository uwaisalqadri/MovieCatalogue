package com.masuwes.core.data.repository

import com.masuwes.core.data.mapper.response.TvShowMapper
import com.masuwes.core.data.source.local.TvShowsDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.utils.AppExecutors
import io.reactivex.Single

class TvShowRepositoryImpl(
        private val apiService: ApiService,
        private val itemTvShowMapper: TvShowMapper,
        private val tvShowsDao: TvShowsDao,
        val executors: AppExecutors
) : TvShowRepository {
    override fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<TvShow>> {
        return apiService.getTvShows(api_key, language, sort_by, page
        ).map {
            itemTvShowMapper.mapToListDomain(it.results)
        }
    }

//    override fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShowEntity> = tvShowsDao.getFavTvPagination()
//
//    override fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShowEntity>>> {
//        return object :
//                NetworkBoundResource<PagedList<DetailTvShowEntity>, List<DetailTvShowEntity>>(
//                        executors
//                ) {
//            override fun loadFromDB(): LiveData<PagedList<DetailTvShowEntity>> {
//                return LivePagedListBuilder(
//                        getTvShowsAsPaged(), 20
//                ).build()
//            }
//
//            override fun shouldFetch(data: PagedList<DetailTvShowEntity>?): Boolean {
//                return false
//            }
//
//            override fun createCall(): LiveData<ApiResponse<List<DetailTvShowEntity>>>? {
//                return null
//            }
//
//            override fun saveCallResult(data: List<DetailTvShowEntity>) {
//
//            }
//        }.asLiveData()
//    }
}