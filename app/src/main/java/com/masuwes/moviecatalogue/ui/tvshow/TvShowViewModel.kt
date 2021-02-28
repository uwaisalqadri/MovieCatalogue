package com.masuwes.moviecatalogue.ui.tvshow

import androidx.lifecycle.MutableLiveData
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.RxUtils

class TvShowViewModel(private val tvShowUseCase: TvShowUseCase) : BaseViewModel() {

    val postTvShowData = MutableLiveData<TvShowState>()
    val messageData = MutableLiveData<String>()
    val showProgressBar = MutableLiveData<Boolean>()

    fun getTvShows(page: Int) {
        postTvShowData.value = LoadingState
        compositeDisposable.add(
            tvShowUseCase.getTvShows(Constants.API_KEY, Constants.LANG, Constants.SORT_BY, page)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postTvShowData.value = TvShowLoadedState(result)
                        showProgressBar.value = false
                    } else {
                        if (page == 1) postTvShowData.value = DataNotFoundState
                        else postTvShowData.value = LastPageState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message.toString()
    }
}

sealed class TvShowState
data class TvShowLoadedState(val tvShow: List<TvShow>) : TvShowState()
object LoadingState : TvShowState()
object LastPageState : TvShowState()
object DataNotFoundState : TvShowState()