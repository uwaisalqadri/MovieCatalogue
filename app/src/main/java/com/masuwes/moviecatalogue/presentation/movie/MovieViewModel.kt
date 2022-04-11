package com.masuwes.moviecatalogue.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.utils.ui.Result
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _moviesResult = MutableLiveData<Result<List<Movie>>>()
    val moviesResult: LiveData<Result<List<Movie>>> get() = _moviesResult

    init {
        _moviesResult.value = Result.default()
    }

    fun getMovies(page: Int) = viewModelScope.launch {
        _moviesResult.value = Result.loading()
        collectFlow(_moviesResult) {
            movieUseCase.getMovies("", page)
        }
    }

}

sealed class MovieState
data class MovieDataLoaded(val movie: List<Movie>) : MovieState()
object LoadingState : MovieState()
object LastPageState : MovieState()
object DataNotFoundState : MovieState()