package com.masuwes.core.domain.base

data class ApiError(
    val statusCode: String,
    val statusMessage: String,
): Exception()