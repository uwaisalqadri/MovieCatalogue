package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .into(this)

}

fun Context.showToast(msg: String) {
    Toast.makeText(
        this,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}

fun ProgressBar.isRefresh(state: Boolean) {
    visibility = when(state) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}