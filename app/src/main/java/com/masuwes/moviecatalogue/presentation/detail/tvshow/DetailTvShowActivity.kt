package com.masuwes.moviecatalogue.presentation.detail.tvshow

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.ui.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailTvShowActivity: BaseActivity<ActivityDetailTvShowBinding>() {

    private val viewModel: DetailTvShowViewModel by inject()

    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding

    private var isFavorite: Boolean = false
    private var tvShowDetail: DetailTvShow? = null
    private var tvShowId: Int = 0

    companion object {
        const val SHOW_ID = "show_id"
        const val PATH = "com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowActivity"

        @JvmStatic
        fun start(context: Context, tvShowId: Int) {
            val starter = Intent(context, DetailTvShowActivity::class.java)
            starter.putExtra(SHOW_ID, tvShowId)
            context.startActivity(starter)
        }

        @JvmStatic
        fun startFromOutside(context: Context, movieId: Int) {
            try {
                val starter = Intent(context, Class.forName(PATH))
                starter.putExtra(SHOW_ID, movieId)
                context.startActivity(starter)
            } catch (e: Exception) {
                Timber.e("Activity Not Found")
            }
        }
    }

    override fun getViewBinding(): ActivityDetailTvShowBinding =
        ActivityDetailTvShowBinding.inflate(layoutInflater)

    override fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            infoBinding = includeInfo
            overviewBinding = includeOverview
        }
    }

    override fun initIntent() {
        tvShowId = intent.getIntExtra(SHOW_ID, 0)
    }

    override fun initAction() {
        binding.fabDetailTvshow.setOnClickListener {
            when(isFavorite) {
                true -> tvShowDetail?.id?.let { viewModel.removeFavTVShow(it) }
                false -> tvShowDetail?.let { viewModel.saveFavTVShow(it) }
                else -> tvShowDetail?.let { viewModel.saveFavTVShow(it) }
            }
        }
    }

    override fun initProcess() {
        viewModel.getDetailTvShow(tvShowId)
    }

    override fun initObservers() {
        viewModel.detailTvShowResult.observeLiveData(owner = this,
            context = this,
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setDetailTvShow(it)
                tvShowDetail = it
            }
        )
    }

    private fun setDetailTvShow(detailTvShow: DetailTvShow) {
        infoBinding.apply {
            backdropImageDetail.loadImage(Constants.URL_IMAGE + detailTvShow.backdrop_path)
            posterImageDetail.loadImage(Constants.URL_IMAGE + detailTvShow.posterPath)
            titleDetail.text = detailTvShow.name
            dateLangDetail.text = " ${detailTvShow.firstAirDate?.formatDate()} . ${detailTvShow.originalLanguage}"
            rateDetail.text = detailTvShow.voteAverage.toString()
            popularDetail.text = detailTvShow.popularity.toString()
        }

        overviewBinding.overviewDetail.text = detailTvShow.overview
        supportActionBar?.title = detailTvShow.originalName
    }

    override fun showLoading() {
        infoBinding.progressCircularDetail.isVisible = true
    }

    override fun hideLoading() {
        infoBinding.progressCircularDetail.isVisible = false
    }

//    viewModel.detailTvShowState.observe(this, detailObserver)
//    idShow?.let { viewModel.getDetailTvShow(it) }
//    idShow?.let { viewModel.checkFavTVShow(it) }
//    @SuppressLint("SetTextI18n")
//    private val detailObserver = Observer<DetailTvShowState> { detailTvShow ->
//        when(detailTvShow) {
//
//            is DetailTvShowLoaded -> {
//                dataTvShow = detailTvShow.detailTvShow
//                val dataTvShow = detailTvShow.detailTvShow
//                infoBinding.apply {
//                    backdropImageDetail.loadImage(Constants.URL_IMAGE + dataTvShow.backdrop_path)
//                    posterImageDetail.loadImage(Constants.URL_IMAGE + dataTvShow.poster_path)
//                    titleDetail.text = dataTvShow.name
//                    dateLangDetail.text = " ${dataTvShow.first_air_date?.formatDate()} . ${dataTvShow.original_language}"
//                    rateDetail.text = dataTvShow.vote_average.toString()
//                    popularDetail.text = dataTvShow.popularity.toString()
//
//                }
//                overviewBinding.overviewDetail.text = dataTvShow.overview
//                supportActionBar?.title = dataTvShow.original_name
//            }
//
//            is FavTVShowSave -> {
//                binding.fabDetailTvshow.apply {
//                    setImageResource(R.drawable.ic_baseline_favorite)
//                    snackBar(getString(R.string.success))
//                }
//                isFavorite = true
//            }
//
//            is RemoveTVShowFav -> {
//                binding.fabDetailTvshow.apply {
//                    setImageResource(R.drawable.ic_favorite_border)
//                    snackBar(getString(R.string.success_remove))
//                }
//                isFavorite = false
//            }
//
//            is DataNotFoundState -> showToast("${R.string.success}")
//
//            is FavTVShowDataFound -> {
//                detailTvShow.detailTvShow.map {
//                    if (it.id == idShow) {
//                        binding.fabDetailTvshow.setImageResource(R.drawable.ic_baseline_favorite)
//                        isFavorite = true
//                    }
//                }
//            }
//
//            else -> {}
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}























