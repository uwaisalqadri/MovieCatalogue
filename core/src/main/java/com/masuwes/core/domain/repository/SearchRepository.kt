package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.entity.search.SearchEntity
import com.masuwes.core.data.model.response.search.SearchItem
import com.masuwes.core.data.model.response.search.SearchResponse

interface SearchRepository {

    suspend fun getSearch(query: String, page: Int): List<SearchItem>

    suspend fun getSearchHistories(): List<SearchEntity>

    suspend fun insertHistory(search: SearchEntity)

    suspend fun deleteAllHistories()
}