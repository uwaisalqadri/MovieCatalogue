package com.masuwes.moviecatalogue.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import com.masuwes.moviecatalogue.data.local.dao.MoviesDao
import com.masuwes.moviecatalogue.di.getExecutor
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.usecase.detail.DetailUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import com.masuwes.moviecatalogue.utils.RxUtils
import java.util.concurrent.Executor

// Detail Movie
sealed class DetailMovieState
data class DetailMovieLoaded(val detailMovie: DetailMovie) : DetailMovieState()
data class FavMovieDataFound(val detailMovie: List<DetailMovie>) : DetailMovieState()
object LoadingState : DetailMovieState()
object DataNotFoundState : DetailMovieState()
object FavMovieSave : DetailMovieState()
object RemoveMovieFav : DetailMovieState()


class DetailMovieVM(
    private val detailUseCase: DetailUseCase,
    private val moviesDao: MoviesDao,
    private val executor: Executor
) : BaseViewModel() {

    val detailMovieState = MutableLiveData<DetailMovieState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun saveFavMovie(movie: DetailMovie) {
        executor.execute { moviesDao.insertFavoriteMovie(movie) }
        detailMovieState.value = FavMovieSave
    }

    fun removeFavMovie(idMovie: Int) {
        executor.execute { moviesDao.deleteFavoriteMovie(idMovie) }
        detailMovieState.value = RemoveMovieFav
    }

    fun checkFavMovie(id: Int) {
        compositeDisposable.add(
            moviesDao.getFavoriteMovieById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailMovieState.value = FavMovieDataFound(result)
                    }
                }, this::onError)
        )
    }

    fun getDetailMovie(idMovie: Int) {
        EspressoIdlingResource.increment()
        detailMovieState.value = LoadingState
        compositeDisposable.add(
            detailUseCase.getDetailMovie(idMovie, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailMovieState.value = DetailMovieLoaded(result)
                        EspressoIdlingResource.decrement()
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























