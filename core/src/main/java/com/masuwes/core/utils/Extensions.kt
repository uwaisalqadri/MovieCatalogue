package com.masuwes.core.utils

import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.masuwes.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placholder_image)
        .fallback(R.drawable.placholder_image)
        .into(this)

}

fun String.formatDate() : String {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O && this.isNotEmpty()) {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
        val currentDate = LocalDate.parse(this, formatter)
        currentDate.format(DateTimeFormatter.ofPattern(Constants.FORMATTED_DATE))
    } else {
        if (this.isEmpty()) "Unknown Date" else this
    }
}