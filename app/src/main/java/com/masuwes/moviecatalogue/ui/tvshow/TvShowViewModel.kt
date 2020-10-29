package com.masuwes.moviecatalogue.ui.tvshow

import androidx.lifecycle.MutableLiveData
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import com.masuwes.moviecatalogue.utils.RxUtils
import timber.log.Timber

class TvShowViewModel(private val tvShowUseCase: TvShowUseCase) : BaseViewModel() {

    val postTvShowData = MutableLiveData<List<TvShow>>()
    val messageData = MutableLiveData<String>()
    val showProgressBar = MutableLiveData<Boolean>()

    fun getTvShows() {
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            tvShowUseCase.getTvShows(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, 1)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postTvShowData.value = result
                        showProgressBar.value = false
                        EspressoIdlingResource.decrement()
                    } else {
                        messageData.value = "${R.string.error}"
                        Timber.e("${R.string.error}")
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }

}