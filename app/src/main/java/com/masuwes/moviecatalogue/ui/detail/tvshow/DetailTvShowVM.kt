package com.masuwes.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.MutableLiveData
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.RxUtils

class DetailTvShowVM(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    val detailTvShowState = MutableLiveData<DetailTvShowState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun saveFavTVShow(tvShow: DetailTvShow) {
        detailUseCase.insertFavoriteTv(tvShow)
        detailTvShowState.value = FavTVShowSave
    }

    fun removeFavTVShow(idShow: Int) {
        detailUseCase.deleteFavoriteTv(idShow)
        detailTvShowState.value = RemoveTVShowFav
    }

    fun checkFavTVShow(id: Int) {
        compositeDisposable.add(
            detailUseCase.getFavoriteTvById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailTvShowState.value = FavTVShowDataFound(result)
                    }
                }, this::onError)
        )
    }

    fun getDetailTvShow(idShow: Int) {
        showProgressBar.value = true
        compositeDisposable.add(
            detailUseCase.getDetailTvShow(idShow, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailTvShowState.value = DetailTvShowLoaded(result)
                        showProgressBar.value = false
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


sealed class DetailTvShowState
data class DetailTvShowLoaded(val detailTvShow: DetailTvShow) : DetailTvShowState()
data class FavTVShowDataFound(val detailTvShow: List<DetailTvShow>) : DetailTvShowState()
object DataNotFoundState : DetailTvShowState()
object FavTVShowSave : DetailTvShowState()
object RemoveTVShowFav : DetailTvShowState()















