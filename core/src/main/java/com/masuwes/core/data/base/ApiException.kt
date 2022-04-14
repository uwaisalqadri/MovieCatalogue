package com.masuwes.core.data.base

import com.masuwes.core.domain.base.ApiError

data class ApiException(
    val statusCode: String,
    val statusMessage: String,
): Exception() {
    fun map(): ApiError {
        return ApiError(statusCode, statusMessage)
    }
}