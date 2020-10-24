package com.masuwes.moviecatalogue.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.usecase.MovieUseCase
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.RxUtils
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {

    val postMovieData = MutableLiveData<List<Movie>>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getMovies() {
        showProgressBar.value = true
        compositeDisposable.add(
            movieUseCase.getMovies(Constants.API_KEY, Constants.LANG, 1)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postMovieData.value = result
                        showProgressBar.value = false
                    } else {
                        messageData.value = "${R.string.error}"
                        Log.d("Error","${R.string.error}")
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }

}