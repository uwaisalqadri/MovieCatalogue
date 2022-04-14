package com.masuwes.core.data.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results")
    val data: T,
    @SerializedName("status_code")
    val statusCode: String,
    @SerializedName("status_message")
    val statusMessage: String
)