package com.masuwes.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    var movieId: String?,
    var title: String?,
    var description: String?,
    var image: String,
    var date: String?,
    var rate: String?
) : Parcelable