package com.masuwes.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowModel(
    var tvId: String?,
    var title: String?,
    var description: String?,
    var image: String,
    var date: String?,
    var rate: String?
) : Parcelable