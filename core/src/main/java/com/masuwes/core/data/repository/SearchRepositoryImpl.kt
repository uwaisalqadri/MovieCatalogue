package com.masuwes.core.data.repository

import androidx.lifecycle.LiveData
import com.masuwes.core.data.mapper.SearchMapper
import com.masuwes.core.data.source.local.SearchHistoryDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.repository.SearchRepository
import io.reactivex.Single
import java.util.concurrent.Executor

class SearchRepositoryImpl(
    private val apiService: ApiService,
    private val searchHistoryDao: SearchHistoryDao,
    private val executor: Executor,
    private val itemSearchMapper: SearchMapper
) : SearchRepository {

    override fun searchAll(
        api_key: String,
        language: String,
        query: String,
        page: Int
    ): Single<List<Search>> = apiService.searchAll(api_key, language, query, page).map {
        itemSearchMapper.mapToListDomain(it.results)
    }

    override fun getSearchHistories(): LiveData<List<Search>> =
        searchHistoryDao.getSearchHistories()

    override fun insertHistory(search: Search) {
        executor.execute { searchHistoryDao.insertHistory(search) }
    }

    override fun deleteAllHistories() {
        executor.execute { searchHistoryDao.deleteAllHistories() }
    }
}