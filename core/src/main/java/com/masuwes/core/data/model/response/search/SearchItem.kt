package com.masuwes.core.data.model.response.search

data class SearchItem(
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