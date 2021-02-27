package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.repository.DetailRepository
import io.reactivex.Single

class DetailInteractor(private val detailRepository: DetailRepository) : DetailUseCase {
    override fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ) = detailRepository.getDetailMovie(movie_id, api_key, language)

    override fun getFavoriteMovieById(idMovie: Int): Single<List<DetailMovie>> =
        detailRepository.getFavoriteMovieById(idMovie)

    override fun insertFavoriteMovie(movies: DetailMovie) {
        detailRepository.insertFavoriteMovie(movies)
    }

    override fun deleteFavoriteMovie(idMovie: Int) {
        detailRepository.deleteFavoriteMovie(idMovie)
    }

    override fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String
    ) = detailRepository.getDetailTvShow(tv_id, api_key, language)

    override fun getFavoriteTvById(idTv: Int): Single<List<DetailTvShow>> =
        detailRepository.getFavoriteTvById(idTv)

    override fun insertFavoriteTv(tvShow: DetailTvShow) {
        detailRepository.insertFavoriteTv(tvShow)
    }

    override fun deleteFavoriteTv(idTv: Int) {
        detailRepository.deleteFavoriteTv(idTv)
    }
}