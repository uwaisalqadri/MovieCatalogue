package com.masuwes.core.data.model.tvshow

data class ResponseTvShow(
    val page: Int,
    val results: List<TvShowItem>,
    val total_pages: Int,
    val total_results: Int
)