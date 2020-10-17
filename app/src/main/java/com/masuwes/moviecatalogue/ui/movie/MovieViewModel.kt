package com.masuwes.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.masuwes.moviecatalogue.model.MovieModel
import com.masuwes.moviecatalogue.utils.Dummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieModel> = Dummy.generateDummyMovie()
}