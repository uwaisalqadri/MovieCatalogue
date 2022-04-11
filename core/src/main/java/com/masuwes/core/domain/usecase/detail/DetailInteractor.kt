package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.data.mapper.response.map
import com.masuwes.core.data.mapper.response.mapEntity
import com.masuwes.core.domain.base.execute
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.repository.DetailRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class DetailInteractor(private val detailRepository: DetailRepository): DetailUseCase {
    override suspend fun getDetailMovie(movieId: Int): Flow<DetailMovie> {
        return execute {
            detailRepository.getDetailMovie(movieId).map()
        }
    }

    override fun getFavoriteMovieById(movieId: Int): Flow<List<DetailMovie>> {
        return execute {
            detailRepository.getFavoriteMovieById(movieId).map()
        }
    }

    override suspend fun insertFavoriteMovie(movie: DetailMovie) {
        detailRepository.insertFavoriteMovie(movie.mapEntity())
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        detailRepository.deleteFavoriteMovie(movieId)
    }

    override suspend fun getDetailTvShow(tvId: Int): Flow<DetailTvShow> {
        return execute {
            detailRepository.getDetailTvShow(tvId).map()
        }
    }

    override fun getFavoriteTvById(tvId: Int): Flow<List<DetailTvShow>> {
        return execute {
            detailRepository.getFavoriteTvById(tvId).map()
        }
    }

    override suspend fun insertFavoriteTv(tvShow: DetailTvShow) {
        detailRepository.insertFavoriteTv(tvShow.mapEntity())
    }

    override suspend fun deleteFavoriteTv(tvId: Int) {
        detailRepository.deleteFavoriteTv(tvId)
    }

}