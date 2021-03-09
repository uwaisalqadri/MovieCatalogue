package com.masuwes.moviecatalogue.ui.detail.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.masuwes.moviecatalogue.databinding.IncludeInfoBinding
import com.masuwes.moviecatalogue.databinding.IncludeOverviewBinding
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.masuwes.moviecatalogue.utils.ui.snackBar
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailTvShowActivity : AppCompatActivity(R.layout.activity_detail_tv_show) {

    companion object {
        const val SHOW_ID = "show_id"
    }

    private val binding: ActivityDetailTvShowBinding by viewBinding()
    private lateinit var infoBinding: IncludeInfoBinding
    private lateinit var overviewBinding: IncludeOverviewBinding

    private val viewModel: DetailTvShowVM by inject()
    private var isFavorite: Boolean? = null
    private var dataTvShow: DetailTvShow? = null
    private var idShow: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoBinding = binding.includeInfo
        overviewBinding = binding.includeOverview
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        idShow = extras?.getInt(SHOW_ID)
        viewModel.detailTvShowState.observe(this, detailObserver)
        idShow?.let { viewModel.getDetailTvShow(it) }
//        idShow?.let { viewModel.checkFavTVShow(it) }

        binding.fabDetailTvshow.setOnClickListener {
//            when(isFavorite) {
//                true -> dataTvShow?.id?.let { viewModel.removeFavTVShow(it) }
//                false -> dataTvShow?.let { viewModel.saveFavTVShow(it) }
//                else -> dataTvShow?.let { viewModel.saveFavTVShow(it) }
//            }
        }

        with(viewModel) {
            messageData.observe(this@DetailTvShowActivity, { messageInfo ->
                showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(this@DetailTvShowActivity, {
                infoBinding.progressCircularDetail.isVisible = it
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private val detailObserver = Observer<DetailTvShowState> { detailTvShow ->
        when(detailTvShow) {

            is DetailTvShowLoaded -> {
                dataTvShow = detailTvShow.detailTvShow
                val dataTvShow = detailTvShow.detailTvShow
                infoBinding.apply {
                    backdropImageDetail.loadImage(Constants.URL_IMAGE + dataTvShow.backdrop_path)
                    posterImageDetail.loadImage(Constants.URL_IMAGE + dataTvShow.poster_path)
                    titleDetail.text = dataTvShow.name
                    dateLangDetail.text = " ${dataTvShow.first_air_date?.formatDate()} . ${dataTvShow.original_language}"
                    rateDetail.text = dataTvShow.vote_average.toString()
                    popularDetail.text = dataTvShow.popularity.toString()

                }
                overviewBinding.overviewDetail.text = dataTvShow.overview
                supportActionBar?.title = dataTvShow.original_name
            }

            is FavTVShowSave -> {
                binding.fabDetailTvshow.apply {
                    setImageResource(R.drawable.ic_baseline_favorite)
                    snackBar(getString(R.string.success))
                }
                isFavorite = true
            }

            is RemoveTVShowFav -> {
                binding.fabDetailTvshow.apply {
                    setImageResource(R.drawable.ic_favorite_border)
                    snackBar(getString(R.string.success_remove))
                }
                isFavorite = false
            }

            is DataNotFoundState -> showToast("${R.string.success}")

            is FavTVShowDataFound -> {
                detailTvShow.detailTvShow.map {
                    if (it.id == idShow) {
                        binding.fabDetailTvshow.setImageResource(R.drawable.ic_baseline_favorite)
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























