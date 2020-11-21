package com.masuwes.moviecatalogue.ui.detail.tvshow

import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel

class DetailTvShowVM(detailTvShowUseCase: TvShowUseCase) : BaseViewModel() {
    override fun onError(error: Throwable) {
        TODO("Not yet implemented")
    }
}