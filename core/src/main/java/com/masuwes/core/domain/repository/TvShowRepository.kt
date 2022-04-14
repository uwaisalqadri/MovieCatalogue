package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.detail.entity.DetailTvShowEntity
import com.masuwes.core.data.model.tvshow.TvShowItem

interface TvShowRepository {

    suspend fun getTvShows(sortBy: String, page: Int): List<TvShowItem>

    suspend fun getFavoriteTv(): List<DetailTvShowEntity>

//    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShowItem>
//
//    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShowItem>>>

}