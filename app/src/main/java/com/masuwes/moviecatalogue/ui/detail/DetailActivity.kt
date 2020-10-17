package com.masuwes.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.model.MovieModel
import com.masuwes.moviecatalogue.model.TvShowModel
import com.masuwes.moviecatalogue.utils.Dummy
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie_id"
        const val SHOW = "show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.apply {
            title = resources.getString(R.string.detail)
            setDisplayHomeAsUpEnabled(true)
        }

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        val extras = intent.extras
        val movieId = extras?.getString(MOVIE)
        val tvId = extras?.getString(SHOW)

        if (movieId != null) {
            viewModel.selectedMovies(movieId)
            for (movieE in Dummy.generateDummyMovie()) {
                if (movieE.movieId == movieId) {
                    populateMovie(viewModel.getMovie())
                }
            }
        } else if (tvId != null) {
            viewModel.selectedShow(tvId)
            for (tvE in Dummy.generateDummyTvShow()) {
                if (tvE.tvId == tvId) {
                    populateTv(viewModel.getTv())
                }
            }
        }
    }

    private fun populateMovie(movies: MovieModel) {
        title_detail.text = movies.title
        date_detail.text = movies.date
        rate_detail.text = movies.rate
        overview_detail.text = movies.description
        image_detail.loadImage(movies.image)
    }

    private fun populateTv(shows: TvShowModel) {
        title_detail.text = shows.title
        date_detail.text = shows.date
        rate_detail.text = shows.rate
        overview_detail.text = shows.description
        image_detail.loadImage(shows.image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}






















