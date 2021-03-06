package com.masuwes.moviecatalogue.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.RxUtils

class DetailMovieVM(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    val detailMovieState = MutableLiveData<DetailMovieState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun saveFavMovie(movie: DetailMovie) {
        detailUseCase.insertFavoriteMovie(movie)
        detailMovieState.value = FavMovieSave
    }

    fun removeFavMovie(idMovie: Int) {
        detailUseCase.deleteFavoriteMovie(idMovie)
        detailMovieState.value = RemoveMovieFav
    }

    fun checkFavMovie(id: Int) {
        compositeDisposable.add(
            detailUseCase.getFavoriteMovieById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailMovieState.value = FavMovieDataFound(result)
                    }
                }, this::onError)
        )
    }

    fun getDetailMovie(idMovie: Int) {
        showProgressBar.value = true
        compositeDisposable.add(
            detailUseCase.getDetailMovie(idMovie, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailMovieState.value = DetailMovieLoaded(result)
                        showProgressBar.value = false
                    } else {
                        detailMovieState.value = DataNotFoundState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message.toString()
    }
}

sealed class DetailMovieState
data class DetailMovieLoaded(val detailMovie: DetailMovie) : DetailMovieState()
data class FavMovieDataFound(val detailMovie: List<DetailMovie>) : DetailMovieState()
object DataNotFoundState : DetailMovieState()
object FavMovieSave : DetailMovieState()
object RemoveMovieFav : DetailMovieState()























