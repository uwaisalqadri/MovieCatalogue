package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.domain.repository.DetailRepository

class DetailInteractor(private val detailRepository: DetailRepository) : DetailUseCase {
    override fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ) = detailRepository.getDetailMovie(movie_id, api_key, language)

    override fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String
    ) = detailRepository.getDetailTvShow(tv_id, api_key, language)

}