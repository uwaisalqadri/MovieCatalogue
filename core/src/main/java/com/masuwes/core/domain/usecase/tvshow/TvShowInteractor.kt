package com.masuwes.core.domain.usecase.tvshow

import com.masuwes.core.data.mapper.response.map
import com.masuwes.core.domain.base.execute
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow

class TvShowInteractor(private val tvShowRepository: TvShowRepository): TvShowUseCase {

    override fun getTvShows(sortBy: String, page: Int): Flow<List<TvShow>> {
        return execute {
            tvShowRepository.getTvShows(sortBy, page).map()
        }
    }
}