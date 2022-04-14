package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.search.entity.SearchEntity
import com.masuwes.core.data.model.search.response.SearchItem

interface SearchRepository {

    suspend fun getSearch(query: String, page: Int): List<SearchItem>

    suspend fun getSearchHistories(): List<SearchEntity>

    suspend fun insertHistory(search: SearchEntity)

    suspend fun deleteAllHistories()
}