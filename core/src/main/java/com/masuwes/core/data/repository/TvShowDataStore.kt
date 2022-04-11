package com.masuwes.core.data.repository

import com.masuwes.core.data.model.response.tvshow.TvShowItem
import com.masuwes.core.data.source.local.TvShowsDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.TvShowRepository

class TvShowDataStore(
    private val apiService: ApiService,
    private val tvShowsDao: TvShowsDao,
): TvShowRepository {

    override suspend fun getTvShows(sortBy: String, page: Int): List<TvShowItem> {
        return apiService.getTvShows(sortBy, page).results
    }

}