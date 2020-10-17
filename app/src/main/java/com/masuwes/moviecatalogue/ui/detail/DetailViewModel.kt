package com.masuwes.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.masuwes.moviecatalogue.model.MovieModel
import com.masuwes.moviecatalogue.model.TvShowModel
import com.masuwes.moviecatalogue.utils.Dummy

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvId: String

    fun selectedMovies(movieId: String) {
        this.movieId = movieId
    }

    fun selectedShow(tvId: String) {
        this.tvId = tvId
    }

    fun getMovie(): MovieModel {
        lateinit var movies: MovieModel
        val movieEntities = Dummy.generateDummyMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movies = movieEntity
            }
        }

        return movies
    }

    fun getTv(): TvShowModel {
        lateinit var tvShows: TvShowModel
        val tvShowEntities = Dummy.generateDummyTvShow()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvId == tvId) {
                tvShows = tvShowEntity
            }
        }
        return tvShows
    }
}


















