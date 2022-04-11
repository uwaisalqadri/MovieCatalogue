package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.data.mapper.response.map
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

    override suspend fun getDetailTvShow(tvId: Int): Flow<DetailTvShow> {
        return execute {
            detailRepository.getDetailTvShow(tvId).map()
        }
    }

}