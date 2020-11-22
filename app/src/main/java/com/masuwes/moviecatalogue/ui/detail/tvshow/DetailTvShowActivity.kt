package com.masuwes.moviecatalogue.ui.detail.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.masuwes.moviecatalogue.utils.ui.showToast
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.include_info.*
import kotlinx.android.synthetic.main.include_overview.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val SHOW_ID = "show_id"
    }

    private val viewModel: DetailTvShowVM by inject()
    var dataTvShow: DetailTvShow? = null
    var idShow: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        idShow = extras?.getInt(SHOW_ID)
        viewModel.detailTvShowState.observe(this, detailObserver)
        idShow?.let { viewModel.getDetailTvShow(it) }
        idShow?.let { viewModel.checkFavTVShow(it) }

        fab_detail_tvshow.setOnClickListener {
            dataTvShow?.let { viewModel.saveFavTVShow(it) }
        }

        with(viewModel) {
            messageData.observe(this@DetailTvShowActivity, Observer { messageInfo ->
                showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(this@DetailTvShowActivity, Observer {
                progress_circular_detail.isRefresh(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private val detailObserver = Observer<DetailTvShowState> { detailTvShow ->
        when(detailTvShow) {

            is DetailTvShowLoaded -> {
                dataTvShow = detailTvShow.detailTvShow
                val dataTvShow = detailTvShow.detailTvShow
                backdrop_image_detail.loadImage(Constants.URL_IMAGE + dataTvShow.backdrop_path)
                poster_image_detail.loadImage(Constants.URL_IMAGE + dataTvShow.poster_path)
                title_detail.text = dataTvShow.name
                date_lang_detail.text = " ${dataTvShow.first_air_date} . ${dataTvShow.original_language}"
                overview_detail.text = dataTvShow.overview
                rate_detail.text = dataTvShow.vote_average.toString()
                popular_detail.text = dataTvShow.popularity.toString()
                supportActionBar?.title = dataTvShow.original_name
            }

            is FavTVShowSave -> {
                fab_detail_tvshow.setImageResource(R.drawable.ic_baseline_favorite)
                showToast("Berhasil ditambahkan")
            }

            is RemoveTVShowFav -> {
                fab_detail_tvshow.setImageResource(R.drawable.ic_favorite_border)
                showToast("Berhasil ditambahkan")
            }

            is DataNotFoundState -> showToast("${R.string.success}")

            is FavTVShowDataFound -> {
                detailTvShow.detailTvShow.map {
                    if (it.id == idShow) {
                        fab_detail_tvshow.setImageResource(R.drawable.ic_baseline_favorite)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}























