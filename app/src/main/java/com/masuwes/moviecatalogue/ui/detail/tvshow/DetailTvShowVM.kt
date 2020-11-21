package com.masuwes.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.MutableLiveData
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import com.masuwes.moviecatalogue.utils.RxUtils

// Detail TvShow
sealed class DetailTvShowState
data class DetailTvShowLoaded(val detailTvShow: DetailTvShow) : DetailTvShowState()
object LoadingState : DetailTvShowState()
object DataNotFoundState : DetailTvShowState()

class DetailTvShowVM(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    val detailTvShowState = MutableLiveData<DetailTvShowState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getDetailTvShow(idShow: Int) {
        EspressoIdlingResource.increment()
        detailTvShowState.value = LoadingState
        compositeDisposable.add(
            detailUseCase.getDetailTvShow(idShow, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailTvShowState.value = DetailTvShowLoaded(result)
                        showProgressBar.value = false
                        EspressoIdlingResource.decrement()
                    } else {
                        detailTvShowState.value = DataNotFoundState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message.toString()
    }
}















