package com.masuwes.moviecatalogue.ui.detail.movie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.masuwes.moviecatalogue.utils.ui.showToast
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.include_info.*
import kotlinx.android.synthetic.main.include_overview.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    private val viewModel: DetailMovieVM by inject()
    var dataMovie: DetailMovie? = null
    var idMovie: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        idMovie = extras?.getInt(MOVIE_ID)
        viewModel.detailMovieState.observe(this, detailObserver)
        idMovie?.let { viewModel.getDetailMovie(it) }

        fab_detail_movie.setOnClickListener {
            fab_detail_movie.setImageResource(R.drawable.ic_baseline_favorite)
        }

        with(viewModel) {
            messageData.observe(this@DetailMovieActivity, Observer { messageInfo ->
                showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(this@DetailMovieActivity, Observer {
                progress_circular_detail.isRefresh(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private val detailObserver = Observer<DetailMovieState> { detailMovie ->
        when(detailMovie) {

            is DetailMovieLoaded -> {
                dataMovie = detailMovie.detailMovie
                val dataMovie = detailMovie.detailMovie
                backdrop_image_detail.loadImage(Constants.URL_IMAGE + dataMovie.backdrop_path)
                poster_image_detail.loadImage(Constants.URL_IMAGE + dataMovie.poster_path)
                title_detail.text = dataMovie.title
                date_lang_detail.text = " ${dataMovie.release_date} . ${dataMovie.original_language}"
                overview_detail.text = dataMovie.overview
                rate_detail.text = dataMovie.vote_average.toString()
                popular_detail.text = dataMovie.popularity.toString()
                supportActionBar?.title = dataMovie.original_title
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}




















