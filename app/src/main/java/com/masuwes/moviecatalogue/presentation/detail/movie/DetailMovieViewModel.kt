package com.masuwes.moviecatalogue.presentation.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import com.masuwes.moviecatalogue.utils.ui.Result as Result

class DetailMovieViewModel(
    private val detailUseCase: DetailUseCase
): ViewModel() {

    private val _detailMovieResult = MutableLiveData<Result<DetailMovie>>()
    val detailMovieResult: LiveData<Result<DetailMovie>> get() = _detailMovieResult

    init {
        _detailMovieResult.value = Result.default()
    }

    fun saveFavMovie(movie: DetailMovie) = viewModelScope.launch {
//        detailUseCase.insertFavoriteMovie(movie)
//        detailMovieState.value = FavMovieSave
    }

    fun removeFavMovie(idMovie: Int) {
//        detailUseCase.deleteFavoriteMovie(idMovie)
//        detailMovieState.value = RemoveMovieFav
    }

    fun checkFavMovie(id: Int) {

    }

    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        _detailMovieResult.value = Result.loading()
        collectFlow(_detailMovieResult) {
            detailUseCase.getDetailMovie(movieId)
        }
    }
}

sealed class DetailMovieState
data class DetailMovieLoaded(val detailMovie: DetailMovie) : DetailMovieState()
data class FavMovieDataFound(val detailMovie: List<DetailMovie>) : DetailMovieState()
object DataNotFoundState : DetailMovieState()
object FavMovieSave : DetailMovieState()
object RemoveMovieFav : DetailMovieState()























