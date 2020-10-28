package com.masuwes.moviecatalogue.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.loadImage
import com.masuwes.moviecatalogue.utils.showToast
import kotlinx.android.synthetic.main.include_info.*
import kotlinx.android.synthetic.main.include_overview.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by inject()

    companion object {
        const val MOVIE_ID = "movie_id"
        const val SHOW_ID = "show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.detailState.observe(this, startObserver)

        val movieId = intent?.extras?.getString(MOVIE_ID)
        val showId = intent?.extras?.getString(SHOW_ID)

        if (movieId != null) {
            viewModel.getDetailMovie(movieId.toString())
        } else if (showId != null) {
            viewModel.getDetailTvShow(showId.toString())
        }

        with(viewModel) {
            progressBar.observe(this@DetailActivity, Observer {
                if (it == true) progress_circular_detail.visibility = View.VISIBLE
                else progress_circular_detail.visibility = View.GONE
            })

            messageData.observe(this@DetailActivity, Observer { error ->
                showToast(error.toString())
            })
        }
    }

    private val startObserver = Observer<DetailState> { dataState ->
        when(dataState) {
            is DetailMovieDataLoaded -> {
                val dataMovie = dataState.detailMovieDomain
                backdrop_image_detail.loadImage(Constants.URL_IMAGE + dataMovie.backdrop_path)
                poster_image_detail.loadImage(Constants.URL_IMAGE + dataMovie.poster_path)
                title_detail.text = dataMovie.title
                date_lang_detail.text = " ${dataMovie.release_date} . ${dataMovie.original_language}"
                overview_detail.text = dataMovie.overview
                rate_detail.text = dataMovie.vote_average.toString()
                popular_detail.text = dataMovie.popularity.toString()
                supportActionBar?.title = dataMovie.original_title
            }
            is DetailShowDataLoaded -> {
                val dataShow = dataState.detailShowDomain
                backdrop_image_detail.loadImage(Constants.URL_IMAGE + dataShow.backdrop_path)
                poster_image_detail.loadImage(Constants.URL_IMAGE + dataShow.poster_path)
                title_detail.text = dataShow.name
                date_lang_detail.text = " ${dataShow.first_air_date} . ${dataShow.original_language}"
                overview_detail.text = dataShow.overview
                rate_detail.text = dataShow.vote_average.toString()
                popular_detail.text = dataShow.popularity.toString()
                supportActionBar?.title = dataShow.original_name
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}




















