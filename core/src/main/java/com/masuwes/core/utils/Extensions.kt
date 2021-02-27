package com.masuwes.core.utils

import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
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
    lateinit var formattedDate: String
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
        val currentDate = LocalDate.parse(this, formatter)
        formattedDate = currentDate.format(DateTimeFormatter.ofPattern(Constants.FORMATTED_DATE))
    }
    return formattedDate
}