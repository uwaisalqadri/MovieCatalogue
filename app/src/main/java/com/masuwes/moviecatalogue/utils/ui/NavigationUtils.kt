package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.content.Intent
import timber.log.Timber

fun startSearchActivity(context: Context) {
    try {
        val starter = Intent(context, Class.forName("com.masuwes.search.presentation.SearchActivity"))
        context.startActivity(starter)
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}

fun startFavoriteActivity(context: Context) {
    try {
        val starter = Intent(context, Class.forName("com.masuwes.favorite.presentation.FavoriteActivity"))
        context.startActivity(starter)
    } catch (e: Exception) {
        Timber.e("Activity Not Found")
    }
}