package com.masuwes.core.data.repository

import com.masuwes.core.data.mapper.DetailMovieMapper
import com.masuwes.core.data.mapper.DetailTvShowMapper
import com.masuwes.core.data.remote.ApiService
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.repository.DetailRepository
import io.reactivex.Single

class DetailRepositoryImpl(
    private val apiService: ApiService,
    private val itemDetailMovieMapper: DetailMovieMapper,
    private val itemDetailTvShowMapper: DetailTvShowMapper
) : DetailRepository {
    override fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ): Single<DetailMovie> {
        return apiService.getMovieDetail(movie_id, api_key, language).map {
            itemDetailMovieMapper.mapToDomain(it)
        }
    }

    override fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String
    ): Single<DetailTvShow> {
        return apiService.getTvShowDetail(tv_id, api_key, language).map {
            itemDetailTvShowMapper.mapToDomain(it)
        }
    }

}