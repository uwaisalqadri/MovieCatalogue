package com.masuwes.core.data.model.movie

data class ResponseMovie(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)