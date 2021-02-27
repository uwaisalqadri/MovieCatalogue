package com.masuwes.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.utils.Resource
import io.reactivex.Single

interface TvShowRepository {

    fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ) : Single<List<TvShow>>

    fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShow>

    fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShow>>>
}