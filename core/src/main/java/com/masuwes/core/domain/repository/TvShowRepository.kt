package com.masuwes.core.domain.repository

import com.masuwes.core.domain.model.TvShow
import io.reactivex.Single

interface TvShowRepository {

    fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ) : Single<List<TvShow>>
}