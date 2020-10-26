package com.masuwes.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.include_info.*
import kotlinx.android.synthetic.main.include_overview.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by inject()
    private lateinit var idMovie: String
    private lateinit var idTvShow: String

    companion object {
        const val BUNDLE_KEY = "bundle_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getStringExtra(BUNDLE_KEY)
        idMovie = data.toString()
        idTvShow = data.toString()
        viewModel.getDetailMovie(data.toString())

        with(viewModel) {
            postDataMovie.observe(this@DetailActivity, Observer { movies ->
                backdrop_image_detail.loadImage(Constants.URL_IMAGE + movies.backdrop_path)
                poster_image_detail.loadImage(Constants.URL_IMAGE + movies.poster_path)
                title_detail.text = movies.title
                date_lang_detail.text = " ${movies.release_date} . ${movies.original_language} "
                overview_detail.text = movies.overview
            })
        }
    }

    override fun onDestroy() {
        finish()
        super.onDestroy()
    }
}




















