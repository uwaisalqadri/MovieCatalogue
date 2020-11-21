package com.masuwes.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import com.masuwes.moviecatalogue.utils.RxUtils
import timber.log.Timber

sealed class MovieState
data class MovieDataLoaded(val movie: List<Movie>) : MovieState()
object LoadingState : MovieState()
object LastPageState : MovieState()
object DataNotFoundState : MovieState()
class MovieViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {

    val postMovieData = MutableLiveData<MovieState>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getMovies(page: Int) {
        EspressoIdlingResource.increment()
        postMovieData.value = LoadingState
        compositeDisposable.add(
            movieUseCase.getMovies(Constants.API_KEY, Constants.LANG, Constants.SORT_BY,1)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postMovieData.value = MovieDataLoaded(result)
                        showProgressBar.value = false
                        EspressoIdlingResource.decrement()
                    } else {
                        if (page == 1) postMovieData.value = DataNotFoundState
                        else postMovieData.value = LastPageState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }

}