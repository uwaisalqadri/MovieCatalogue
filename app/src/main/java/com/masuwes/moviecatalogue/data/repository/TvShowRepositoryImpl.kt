package com.masuwes.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masuwes.moviecatalogue.data.local.dao.TvShowsDao
import com.masuwes.moviecatalogue.data.mapper.TvShowMapper
import com.masuwes.moviecatalogue.data.remote.ApiService
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.repository.TvShowRepository
import com.masuwes.moviecatalogue.utils.room.ApiResponse
import com.masuwes.moviecatalogue.utils.room.AppExecutors
import com.masuwes.moviecatalogue.utils.room.NetworkBoundResource
import com.masuwes.moviecatalogue.utils.room.Resource
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