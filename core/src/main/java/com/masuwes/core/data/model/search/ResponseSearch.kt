package com.masuwes.core.data.model.search

data class ResponseSearch(
    val page: Int,
    val results: List<SearchItem>,
    val total_pages: Int,
    val total_results: Int
)