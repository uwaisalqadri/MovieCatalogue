package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.content.Intent
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