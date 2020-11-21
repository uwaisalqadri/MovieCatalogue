package com.masuwes.moviecatalogue.domain.usecase.detail

import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import io.reactivex.Single

interface DetailUseCase {

    fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String,
    ) : Single<DetailMovie>

    fun getDetailTvShow(
        tv_id: String,
        api_key: String,
        language: String,
    ) : Single<DetailTvShow>
}