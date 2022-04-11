package com.masuwes.core.domain.usecase.search

import com.masuwes.core.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    fun getSearch(query: String, page: Int): Flow<List<Search>>

    fun getSearchHistories(): Flow<List<Search>>

    suspend fun insertHistory(search: Search)

    suspend fun deleteAllHistories()
}