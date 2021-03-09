package com.masuwes.core.domain.model

data class Search(
    val id: Int,
    val media_type: String?,
    val name: String?,
    val poster_path: String?,
    val title: String?,
    val original_language: String?,
    val first_air_date: String?,
    val release_date: String?,
    val vote_average: Double?,
)