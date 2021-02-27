package com.masuwes.moviecatalogue.ui.detail.movie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.R
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.masuwes.moviecatalogue.utils.ui.snackBar
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding

    private val viewModel: DetailMovieVM by inject()
    private var isFavorite: Boolean? = null
    var dataMovie: DetailMovie? = null
    var idMovie: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        infoBinding = binding.includeInfo
        overviewBinding = binding.includeOverview
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        idMovie = extras?.getInt(MOVIE_ID)
        viewModel.detailMovieState.observe(this, detailObserver)
        idMovie?.let { viewModel.getDetailMovie(it) }
        idMovie?.let { viewModel.checkFavMovie(it) }

        binding.fabDetailMovie.setOnClickListener {
           when(isFavorite) {
               true -> dataMovie?.id?.let { viewModel.removeFavMovie(it) }
               false -> dataMovie?.let { viewModel.saveFavMovie(it) }
               else -> dataMovie?.let { viewModel.saveFavMovie(it) }
           }
        }

        with(viewModel) {
            messageData.observe(this@DetailMovieActivity, Observer { messageInfo ->
                showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(this@DetailMovieActivity, Observer {
                infoBinding.progressCircularDetail.isVisible = it
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private val detailObserver = Observer<DetailMovieState> { detailMovie ->
        when(detailMovie) {

            is DetailMovieLoaded -> {
                dataMovie = detailMovie.detailMovie
                val dataMovie = detailMovie.detailMovie
                infoBinding.apply {
                    backdropImageDetail.loadImage(Constants.URL_IMAGE + dataMovie.backdrop_path)
                    posterImageDetail.loadImage(Constants.URL_IMAGE + dataMovie.poster_path)
                    titleDetail.text = dataMovie.title
                    dateLangDetail.text = " ${dataMovie.release_date?.formatDate()} . ${dataMovie.original_language}"
                    rateDetail.text = dataMovie.vote_average.toString()
                    popularDetail.text = dataMovie.popularity.toString()
                }

                overviewBinding.overviewDetail.text = dataMovie.overview
                supportActionBar?.title = dataMovie.original_title
            }

            is FavMovieSave -> {
                binding.fabDetailMovie.apply {
                    setImageResource(R.drawable.ic_baseline_favorite)
                    snackBar(getString(R.string.success))
                }
                isFavorite = true
            }

            is RemoveMovieFav -> {
                binding.fabDetailMovie.apply {
                    setImageResource(R.drawable.ic_favorite_border)
                    snackBar(getString(R.string.success_remove))
                }
                isFavorite = false
            }

            is DataNotFoundState -> showToast(getString(R.string.error))

            is FavMovieDataFound -> {
                detailMovie.detailMovie.map {
                    if (it.id == idMovie) {
                        binding.fabDetailMovie.setImageResource(R.drawable.ic_baseline_favorite)
                        isFavorite = true
                    }
                }
            }
            else -> {}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}




















