package com.masuwes.moviecatalogue.presentation.detail.movie

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.utils.ui.BaseActivity
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.masuwes.moviecatalogue.utils.ui.observeLiveData
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailMovieActivity: BaseActivity<ActivityDetailMovieBinding>() {

    private val viewModel: DetailMovieViewModel by inject()

    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding

    private var isFavorite: Boolean = false
    private var movieDetail: DetailMovie? = null
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
        binding.fabDetailMovie.setOnClickListener {
            when(isFavorite) {
                true -> movieDetail?.id?.let { viewModel.removeFavMovie(it) }
                false -> movieDetail?.let { viewModel.saveFavMovie(it) }
                else -> movieDetail?.let { viewModel.saveFavMovie(it) }
            }
        }
    }

    override fun initProcess() {
        viewModel.getDetailMovie(movieId)
        Timber.d("anjay tailah")
    }

    override fun initObservers() {
        viewModel.detailMovieResult.observeLiveData(
            owner = this,
            context = this,
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setDetailMovie(it)
                Timber.d(it.toString())
                movieDetail = it
            }
        )
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


    // TODO: Will reuse later

    //viewModel.detailMovieState.observe(this, detailObserver)
    //idMovie?.let { viewModel.getDetailMovie(it) }
    //idMovie?.let { viewModel.checkFavMovie(it) }
//    @SuppressLint("SetTextI18n")
//    private val detailObserver = Observer<DetailMovieState> { detailMovie ->
//        when(detailMovie) {
//
//            is DetailMovieLoaded -> {
//                dataMovie = detailMovie.detailMovie
//                val dataMovie = detailMovie.detailMovie
//                infoBinding.apply {
//                    backdropImageDetail.loadImage(Constants.URL_IMAGE + dataMovie.backdrop_path)
//                    posterImageDetail.loadImage(Constants.URL_IMAGE + dataMovie.poster_path)
//                    titleDetail.text = dataMovie.title
//                    dateLangDetail.text = " ${dataMovie.release_date?.formatDate()} . ${dataMovie.original_language}"
//                    rateDetail.text = dataMovie.vote_average.toString()
//                    popularDetail.text = dataMovie.popularity.toString()
//                }
//
//                overviewBinding.overviewDetail.text = dataMovie.overview
//                supportActionBar?.title = dataMovie.original_title
//            }
//
//            is FavMovieSave -> {
//                binding.fabDetailMovie.apply {
//                    setImageResource(R.drawable.ic_baseline_favorite)
//                    snackBar(getString(R.string.success))
//                }
//                isFavorite = true
//            }
//
//            is RemoveMovieFav -> {
//                binding.fabDetailMovie.apply {
//                    setImageResource(R.drawable.ic_favorite_border)
//                    snackBar(getString(R.string.success_remove))
//                }
//                isFavorite = false
//            }
//
//            is DataNotFoundState -> showToast(getString(R.string.error))
//
//            is FavMovieDataFound -> {
//                detailMovie.detailMovie.map {
//                    if (it.id == movieId) {
//                        binding.fabDetailMovie.setImageResource(R.drawable.ic_baseline_favorite)
//                        isFavorite = true
//                    }
//                }
//            }
//            else -> {}
//        }
//    }

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