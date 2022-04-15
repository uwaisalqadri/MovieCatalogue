package com.masuwes.moviecatalogue.presentation.detail.tvshow

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.utils.base.BaseActivity
import com.masuwes.moviecatalogue.utils.ui.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailTvShowActivity: BaseActivity<ActivityDetailTvShowBinding>() {

    private val viewModel: DetailTvShowViewModel by inject()

    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding
    private lateinit var tvShowDetail: DetailTvShow

    private var isFavorite: Boolean = false
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
        binding.fabDetailTvshow.setOnClickListener { view ->
            if (isFavorite) viewModel.removeFavTVShow(tvShowDetail.id)
            else viewModel.saveFavTVShow(tvShowDetail)

            view.snackBar(getString(if (isFavorite) R.string.success_remove else R.string.success))
        }
    }

    override fun initProcess() {
        viewModel.getDetailTvShow(tvShowId)
        viewModel.getFavoriteTvById(tvShowId)
    }

    override fun initObservers() {
        viewModel.detailTvShowResult.observeData(
            owner = this,
            context = this,
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setDetailTvShow(it)
                tvShowDetail = it
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

    private fun toggleButton(isFavorite: Boolean) {
        this.isFavorite = isFavorite
        binding.fabDetailTvshow.apply {
            setImageResource(if (isFavorite) R.drawable.ic_baseline_favorite else R.drawable.ic_favorite_border)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}