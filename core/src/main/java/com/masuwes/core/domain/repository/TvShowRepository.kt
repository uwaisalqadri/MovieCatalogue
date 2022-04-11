package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.response.tvshow.TvShowItem

interface TvShowRepository {

    suspend fun getTvShows(sortBy: String, page: Int): List<TvShowItem>

//    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShowItem>
//
//    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShowItem>>>

}