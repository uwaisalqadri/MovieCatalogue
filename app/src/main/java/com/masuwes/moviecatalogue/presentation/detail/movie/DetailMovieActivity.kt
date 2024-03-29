package com.masuwes.moviecatalogue.presentation.detail.movie

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.utils.base.BaseActivity
import com.masuwes.moviecatalogue.utils.ui.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailMovieActivity: BaseActivity<ActivityDetailMovieBinding>() {

    private val viewModel: DetailMovieViewModel by inject()

    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding
    private lateinit var movieDetail: DetailMovie

    private var isFavorite: Boolean = false
    private var movieId: Int = 0

    companion object {
        const val MOVIE_ID = "movie"
        const val PATH = "com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity"

        @JvmStatic
        fun start(context: Context, movieId: Int) {
            val starter = Intent(context, DetailMovieActivity::class.java)
            starter.putExtra(MOVIE_ID, movieId)
            context.startActivity(starter)
        }

        @JvmStatic
        fun startFromOutside(context: Context, movieId: Int) {
            try {
                val starter = Intent(context, Class.forName(PATH))
                starter.putExtra(MOVIE_ID, movieId)
                context.startActivity(starter)
            } catch (e: Exception) {
                Timber.e("Activity Not Found")
            }
        }
    }

    override fun getViewBinding(): ActivityDetailMovieBinding =
        ActivityDetailMovieBinding.inflate(layoutInflater)

    override fun initUI() {
        infoBinding = binding.includeInfo
        overviewBinding = binding.includeOverview
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initIntent() {
        movieId = intent.getIntExtra(MOVIE_ID, 0)
    }

    override fun initAction() {
        binding.fabDetailMovie.setOnClickListener { view ->
            if (isFavorite) viewModel.removeFavMovie(movieDetail.id)
            else viewModel.saveFavMovie(movieDetail)

            view.snackBar(getString(if (isFavorite) R.string.success_remove else R.string.success))
        }
    }

    override fun initProcess() {
        viewModel.getDetailMovie(movieId)
        viewModel.getFavoriteMovieById(movieId)
    }

    override fun initObservers() {
        viewModel.detailMovieResult.observeData(
            owner = this,
            context = this,
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setDetailMovie(it)
                movieDetail = it
            },
            onFailure = {
                hideLoading()
            }
        )

        viewModel.favoriteState.observe(this) { favoriteState ->
            when(favoriteState) {
                is FavoriteState.AddFavorite -> {
                    toggleButton(true)
                }
                is FavoriteState.RemoveFavorite -> {
                    toggleButton(false)
                }
                is FavoriteState.FavoriteFound -> {
                    toggleButton(favoriteState.state)
                }
            }
        }
    }

    private fun setDetailMovie(detailMovie: DetailMovie) {
        infoBinding.apply {
            backdropImageDetail.loadImage(Constants.URL_IMAGE + detailMovie.backdropPath)
            posterImageDetail.loadImage(Constants.URL_IMAGE + detailMovie.posterPath)
            titleDetail.text = detailMovie.title
            dateLangDetail.text = " ${detailMovie.releaseDate?.formatDate()} . ${detailMovie.originalLanguage}"
            rateDetail.text = detailMovie.voteAverage.toString()
            popularDetail.text = detailMovie.popularity.toString()
        }

        overviewBinding.overviewDetail.text = detailMovie.overview
        supportActionBar?.title = detailMovie.originalTitle
    }

    private fun toggleButton(isFavorite: Boolean) {
        this.isFavorite = isFavorite

        binding.fabDetailMovie.apply {
            setImageResource(if (isFavorite) R.drawable.ic_baseline_favorite else R.drawable.ic_favorite_border)
        }
    }

    override fun showLoading() {
        infoBinding.progressCircularDetail.isVisible = true
    }

    override fun hideLoading() {
        infoBinding.progressCircularDetail.isVisible = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}