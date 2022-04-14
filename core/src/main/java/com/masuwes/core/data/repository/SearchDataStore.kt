package com.masuwes.core.data.repository

import com.masuwes.core.data.model.search.entity.SearchEntity
import com.masuwes.core.data.model.search.response.SearchItem
import com.masuwes.core.data.source.local.SearchHistoryDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.repository.SearchRepository
import java.util.concurrent.Executor

class SearchDataStore(
    private val apiService: ApiService,
    private val searchHistoryDao: SearchHistoryDao
): SearchRepository {

    override suspend fun getSearch(query: String, page: Int): List<SearchItem> {
        return apiService.getSearch(query, page).data
    }

    override suspend fun getSearchHistories(): List<SearchEntity> {
        return searchHistoryDao.getSearchHistories()
    }

    override suspend fun insertHistory(search: SearchEntity) {
        searchHistoryDao.insertHistory(search)
    }

    override suspend fun deleteAllHistories() {
        searchHistoryDao.deleteAllHistories()
    }


}