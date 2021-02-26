package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import io.reactivex.Single

interface DetailUseCase {

    fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String,
    ) : Single<DetailMovie>

    fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String,
    ) : Single<DetailTvShow>
}