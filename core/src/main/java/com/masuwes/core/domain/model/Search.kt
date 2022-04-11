package com.masuwes.core.domain.model

data class Search(
    val id: Int,
    val mediaType: String,
    val name: String,
    val posterPath: String,
    val title: String,
    val originalLanguage: String,
    val firstAirDate: String,
    val releaseDate: String,
    val voteAverage: Double,
)