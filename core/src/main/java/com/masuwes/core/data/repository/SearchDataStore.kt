package com.masuwes.core.data.repository

import com.masuwes.core.data.model.response.search.SearchItem
import com.masuwes.core.data.source.local.SearchHistoryDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.SearchRepository
import java.util.concurrent.Executor

class SearchDataStore(
    private val apiService: ApiService,
    private val searchHistoryDao: SearchHistoryDao,
    private val executor: Executor
): SearchRepository {

    override suspend fun getSearch(query: String, page: Int): List<SearchItem> {
        return apiService.getSearch(query, page).results
    }

    override suspend fun getSearchHistories(): List<SearchItem> {
        return emptyList()
    }

    override suspend fun insertHistory(search: SearchItem) {
    }

    override suspend fun deleteAllHistories() {
    }


}