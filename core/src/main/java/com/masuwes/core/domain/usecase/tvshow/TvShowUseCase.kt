package com.masuwes.core.domain.usecase.tvshow

import com.masuwes.core.domain.model.TvShow
import io.reactivex.Single

interface TvShowUseCase {

    fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<TvShow>>

//    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShow>
//
//    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShow>>>
}