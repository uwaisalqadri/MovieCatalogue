package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.masuwes.core.R
import com.masuwes.core.utils.loadImage

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placholder_image)
        .fallback(R.drawable.placholder_image)
        .into(this)

}

fun Context.showToast(msg: String) {

    Toast.makeText(
        this,
        msg,
        Toast.LENGTH_SHORT
    ).show()

}

fun View.snackBar(msg: String) {

    Snackbar.make(
            this,
            msg,
            Snackbar.LENGTH_SHORT
    ).show()

}