package com.masuwes.moviecatalogue.domain.usecase.detail

import com.masuwes.moviecatalogue.domain.repository.DetailRepository

class DetailInteractor(private val detailRepository: DetailRepository) : DetailUseCase {
    override fun getDetailMovie(
        movie_id: String,
        api_key: String,
        language: String
    ) = detailRepository.getDetailMovie(movie_id, api_key, language)

    override fun getDetailTvShow(
        tv_id: String,
        api_key: String,
        language: String
    ) = detailRepository.getDetailTvShow(tv_id, api_key, language)

}