package com.masuwes.moviecatalogue.presentation.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.usecase.detail.DetailUseCase
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

    init {
        _detailTvShowResult.value = Result.default()
        _favTvShowResult.value = Result.default()
    }

    fun saveFavTVShow(tvShow: DetailTvShow) = viewModelScope.launch {
//        detailUseCase.insertFavoriteTv(tvShow)
//        detailTvShowState.value = FavTVShowSave
    }

    fun removeFavTVShow(idShow: Int) {
//        detailUseCase.deleteFavoriteTv(idShow)
//        detailTvShowState.value = RemoveTVShowFav
    }

    fun getFavoriteTvById(tvId: Int) = viewModelScope.launch {
        _favTvShowResult.value = Result.loading()
        collectFlow(_favTvShowResult) {
            detailUseCase.getFavoriteTvById(tvId)
        }
    }

    fun getDetailTvShow(tvShowId: Int) = viewModelScope.launch {
        _detailTvShowResult.value = Result.loading()
        collectFlow(_detailTvShowResult) {
            detailUseCase.getDetailTvShow(tvShowId)
        }
    }
}


sealed class DetailTvShowState
data class DetailTvShowLoaded(val detailTvShow: DetailTvShow) : DetailTvShowState()
data class FavTVShowDataFound(val detailTvShow: List<DetailTvShow>) : DetailTvShowState()
object DataNotFoundState : DetailTvShowState()
object FavTVShowSave : DetailTvShowState()
object RemoveTVShowFav : DetailTvShowState()















