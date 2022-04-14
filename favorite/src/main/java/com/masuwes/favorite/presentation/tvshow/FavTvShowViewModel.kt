package com.masuwes.favorite.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.utils.ui.Result
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch

class FavTvShowViewModel(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    private val _favoriteTvShows = MutableLiveData<Result<List<DetailTvShow>>>()
    val favoriteTvShows: LiveData<Result<List<DetailTvShow>>> get() = _favoriteTvShows

    fun getFavoriteTvShow() = viewModelScope.launch {
        _favoriteTvShows.value = Result.loading()
        collectFlow(_favoriteTvShows) {
            tvShowUseCase.getFavoriteTv()
        }
    }

}