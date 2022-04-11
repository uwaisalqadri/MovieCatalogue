package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.content.Intent
import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowActivity
import timber.log.Timber

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