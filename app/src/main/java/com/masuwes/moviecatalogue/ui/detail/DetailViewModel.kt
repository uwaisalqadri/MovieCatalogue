package com.masuwes.moviecatalogue.ui.detail

import androidx.lifecycle.MutableLiveData
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.usecase.movie.DetailUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import com.masuwes.moviecatalogue.utils.RxUtils
import timber.log.Timber

sealed class DetailState
data class DetailMovieDataLoaded(val detailMovieDomain: DetailMovie) : DetailState()
data class DetailShowDataLoaded(val detailShowDomain: DetailTvShow) : DetailState()
class DetailViewModel(private val detailUseCase: DetailUseCase) : BaseViewModel() {

    val detailState = MutableLiveData<DetailState>()
    val messageData = MutableLiveData<String>()
    val showProgressBar = MutableLiveData<Boolean>()

    fun getDetailMovie(idMovie: String) {
        EspressoIdlingResource.increment()
        showProgressBar.value = true
        compositeDisposable.add(
            detailUseCase.getDetailMovie(idMovie, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailState.value = DetailMovieDataLoaded(result)
                        showProgressBar.value = false
                    } else {
                        messageData.value = "${R.string.error}"
                        Timber.i("${R.string.error}")
                    }
                }, this::onError)
        )
    }

    fun getDetailTvShow(idShow: String) {
        EspressoIdlingResource.increment()
        showProgressBar.value = true
        compositeDisposable.add(
            detailUseCase.getDetailTvShow(idShow, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailState.value = DetailShowDataLoaded(result)
                        showProgressBar.value = false
                    } else {
                        messageData.value = "${R.string.error}"
                        Timber.i("${R.string.error}")
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }
}