package com.masuwes.moviecatalogue.data.repository

import com.masuwes.moviecatalogue.data.mapper.TvShowMapper
import com.masuwes.moviecatalogue.data.service.ApiService
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.repository.TvShowRepository
import io.reactivex.Single

class TvShowRepositoryImpl(
    private val apiService: ApiService,
    private val itemTvShowMapper: TvShowMapper
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
}