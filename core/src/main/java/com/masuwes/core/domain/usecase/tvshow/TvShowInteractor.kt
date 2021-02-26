package com.masuwes.core.domain.usecase.tvshow

import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.repository.TvShowRepository
import io.reactivex.Single

class TvShowInteractor(private val tvShowRepository: TvShowRepository) : TvShowUseCase {
    override fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<TvShow>> =
        tvShowRepository.getTvShows(api_key, language, sort_by, page)

}