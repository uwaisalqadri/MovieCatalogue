package com.masuwes.favorite.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.domain.usecase.movie.MovieUseCase
import com.masuwes.moviecatalogue.utils.ui.Result
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val _favoriteMovies = MutableLiveData<Result<List<DetailMovie>>>()
    val favoriteMovies: LiveData<Result<List<DetailMovie>>> get() = _favoriteMovies

    init {
        _favoriteMovies.value = Result.default()
    }

    fun getFavoriteMovie() = viewModelScope.launch {
        _favoriteMovies.value = Result.loading()
        collectFlow(_favoriteMovies) {
            movieUseCase.getFavoriteMovie()
        }
    }
}