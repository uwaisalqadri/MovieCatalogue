package com.masuwes.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masuwes.core.data.local.dao.TvShowsDao
import com.masuwes.core.data.mapper.TvShowMapper
import com.masuwes.core.data.remote.ApiService
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.utils.ApiResponse
import com.masuwes.core.utils.AppExecutors
import com.masuwes.core.utils.NetworkBoundResource
import com.masuwes.core.utils.Resource
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

    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShow> = tvShowsDao.getFavTvPagination()

    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShow>>> {
        return object :
                NetworkBoundResource<PagedList<DetailTvShow>, List<DetailTvShow>>(
                        executors
                ) {
            override fun loadFromDB(): LiveData<PagedList<DetailTvShow>> {
                return LivePagedListBuilder(
                        getTvShowsAsPaged(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<DetailTvShow>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<DetailTvShow>>>? {
                return null
            }

            override fun saveCallResult(data: List<DetailTvShow>) {

            }
        }.asLiveData()
    }
}