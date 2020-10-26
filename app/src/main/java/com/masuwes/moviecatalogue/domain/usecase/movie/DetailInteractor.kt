package com.masuwes.moviecatalogue.domain.usecase.movie

import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import io.reactivex.Single

class DetailInteractor(
    private val detailUseCase: DetailUseCase
) : DetailUseCase {
    override fun getDetailMovie(
        movie_id: String,
        api_key: String,
        language: String
    ): Single<DetailMovie> {
        return detailUseCase.getDetailMovie(movie_id, api_key, language)
    }

    override fun getDetailTvShow(
        tv_id: String,
        api_key: String,
        language: String
    ): Single<DetailTvShow> {
        return detailUseCase.getDetailTvShow(tv_id, api_key, language)
    }
}