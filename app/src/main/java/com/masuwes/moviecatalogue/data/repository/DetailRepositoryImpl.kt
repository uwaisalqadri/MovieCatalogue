package com.masuwes.moviecatalogue.data.repository

import com.masuwes.moviecatalogue.data.mapper.DetailMovieMapper
import com.masuwes.moviecatalogue.data.mapper.DetailTvShowMapper
import com.masuwes.moviecatalogue.data.remote.ApiService
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.repository.DetailRepository
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
        tv_id: String,
        api_key: String,
        language: String
    ): Single<DetailTvShow> {
        return apiService.getTvShowDetail(tv_id, api_key, language).map {
            itemDetailTvShowMapper.mapToDomain(it)
        }
    }

}