package com.masuwes.moviecatalogue.presentation.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.utils.ui.FavoriteState
import com.masuwes.moviecatalogue.utils.ui.Result
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch

class DetailTvShowViewModel(
    private val detailUseCase: DetailUseCase
): ViewModel() {

    private val _detailTvShowResult = MutableLiveData<Result<DetailTvShow>>()
    val detailTvShowResult: LiveData<Result<DetailTvShow>> get() = _detailTvShowResult

    private val _favTvShowResult = MutableLiveData<Result<List<DetailTvShow>>>()
    val favTvShowResult: LiveData<Result<List<DetailTvShow>>> get() = _favTvShowResult

    private val _favoriteState = MutableLiveData<FavoriteState>()
    val favoriteState: LiveData<FavoriteState> = _favoriteState

    init {
        _detailTvShowResult.value = Result.default()
        _favTvShowResult.value = Result.default()
    }

    fun saveFavTVShow(tvShow: DetailTvShow) = viewModelScope.launch {
        detailUseCase.insertFavoriteTv(tvShow)
        _favoriteState.value = FavoriteState.AddFavorite
    }

    fun removeFavTVShow(tvShowId: Int) = viewModelScope.launch {
        detailUseCase.deleteFavoriteTv(tvShowId)
        _favoriteState.value = FavoriteState.RemoveFavorite
    }

    fun getFavoriteTvById(tvId: Int) = viewModelScope.launch {
        detailUseCase.getFavoriteTvById(tvId).collect { result ->
            result.map { _favoriteState.value = FavoriteState.FavoriteFound(it.id == tvId) }
        }
    }

    fun getDetailTvShow(tvShowId: Int) = viewModelScope.launch {
        _detailTvShowResult.value = Result.loading()
        collectFlow(_detailTvShowResult) {
            detailUseCase.getDetailTvShow(tvShowId)
        }
    }
}