package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.content.Intent
import com.masuwes.core.domain.model.Search
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import timber.log.Timber
import java.lang.Exception

fun openSearchActivity(context: Context) {
    try {
        context.startActivity(Intent(
            context,
            Class.forName("com.masuwes.search.ui.SearchActivity")
        ))
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}

fun openFavoriteActivity(context: Context) {
    try {
        context.startActivity(Intent(
            context,
            Class.forName("com.masuwes.favorite.ui.FavoriteActivity")
        ))
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}

fun openMovieDetailActivity(context: Context, idSearch: Int) {
    try {
        context.startActivity(Intent(
            context,
            Class.forName("com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity")
        ).putExtra(DetailMovieActivity.MOVIE_ID, idSearch))
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}

fun openTvShowDetailActivity(context: Context, idSearch: Int) {
    try {
        context.startActivity(Intent(
            context,
            Class.forName("com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity")
        ).putExtra(DetailTvShowActivity.SHOW_ID, idSearch))
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}