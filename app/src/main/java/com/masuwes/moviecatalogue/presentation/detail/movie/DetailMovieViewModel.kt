package com.masuwes.moviecatalogue.presentation.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.utils.ui.FavoriteState
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch
import com.masuwes.moviecatalogue.utils.ui.Result as Result

class DetailMovieViewModel(
    private val detailUseCase: DetailUseCase
): ViewModel() {

    private val _detailMovieResult = MutableLiveData<Result<DetailMovie>>()
    val detailMovieResult: LiveData<Result<DetailMovie>> get() = _detailMovieResult

    private val _favMovieResult = MutableLiveData<Result<List<DetailMovie>>>()
    val favMovieResult: LiveData<Result<List<DetailMovie>>> get() = _favMovieResult

    private val _favoriteState = MutableLiveData<FavoriteState>()
    val favoriteState: LiveData<FavoriteState> = _favoriteState

    init {
        _detailMovieResult.value = Result.default()
        _favMovieResult.value = Result.default()
    }

    fun saveFavMovie(movie: DetailMovie) = viewModelScope.launch {
        detailUseCase.insertFavoriteMovie(movie)
        _favoriteState.value = FavoriteState.AddFavorite
    }

    fun removeFavMovie(movieId: Int) = viewModelScope.launch {
        detailUseCase.deleteFavoriteMovie(movieId)
        _favoriteState.value = FavoriteState.RemoveFavorite
    }

    fun getFavoriteMovieById(movieId: Int) = viewModelScope.launch {
        detailUseCase.getFavoriteMovieById(movieId).collect { result ->
            result.map { _favoriteState.value = FavoriteState.FavoriteFound(it.id == movieId) }
        }
    }

    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        _detailMovieResult.value = Result.loading()
        collectFlow(_detailMovieResult) {
            detailUseCase.getDetailMovie(movieId)
        }
    }
}























