package com.masuwes.moviecatalogue.domain.repository

import com.masuwes.moviecatalogue.domain.model.TvShow
import io.reactivex.Single

interface TvShowRepository {

    fun getTvShows(
        api_key: String,
        language: String,
        page: Int
    ) : Single<List<TvShow>>
}