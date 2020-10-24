package com.masuwes.moviecatalogue.domain.usecase

import com.masuwes.moviecatalogue.domain.model.TvShow
import io.reactivex.Single

interface TvShowUseCase {

    fun getTvShows(
        api_key: String,
        language: String,
        page: Int
    ) : Single<List<TvShow>>
}