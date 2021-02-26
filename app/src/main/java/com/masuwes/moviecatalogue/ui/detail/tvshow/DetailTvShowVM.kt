package com.masuwes.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.MutableLiveData
import com.masuwes.core.data.local.dao.TvShowsDao
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.core.Constants
import com.masuwes.moviecatalogue.utils.RxUtils
import java.util.concurrent.Executor

// Detail TvShow
sealed class DetailTvShowState
data class DetailTvShowLoaded(val detailTvShow: DetailTvShow) : DetailTvShowState()
data class FavTVShowDataFound(val detailTvShow: List<DetailTvShow>) : DetailTvShowState()
object LoadingState : DetailTvShowState()
object DataNotFoundState : DetailTvShowState()
object FavTVShowSave : DetailTvShowState()
object RemoveTVShowFav : DetailTvShowState()


class DetailTvShowVM(
    private val detailUseCase: DetailUseCase,
    private val tvShowsDao: TvShowsDao,
    private val executor: Executor
) : BaseViewModel() {

    val detailTvShowState = MutableLiveData<DetailTvShowState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun saveFavTVShow(tvShow: DetailTvShow) {
        executor.execute { tvShowsDao.insertFavoriteTv(tvShow) }
        detailTvShowState.value = FavTVShowSave
    }

    fun removeFavTVShow(idShow: Int) {
        executor.execute { tvShowsDao.deleteFavoriteTv(idShow) }
        detailTvShowState.value = RemoveTVShowFav
    }

    fun checkFavTVShow(id: Int) {
        compositeDisposable.add(
            tvShowsDao.getFavoriteTvById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailTvShowState.value = FavTVShowDataFound(result)
                    }
                }, this::onError)
        )
    }

    fun getDetailTvShow(idShow: Int) {
        detailTvShowState.value = LoadingState
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















