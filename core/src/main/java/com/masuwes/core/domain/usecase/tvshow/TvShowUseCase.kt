package com.masuwes.core.domain.usecase.tvshow

import com.masuwes.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {

    fun getTvShows(sortBy: String, page: Int): Flow<List<TvShow>>

//    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShow>
//
//    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShow>>>
}