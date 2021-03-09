package com.masuwes.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.masuwes.core.data.mapper.entity.SearchEntityMapper
import com.masuwes.core.data.mapper.response.SearchMapper
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
        private val itemSearchMapper: SearchMapper,
        private val itemSearchEntityMapper: SearchEntityMapper
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
        Transformations.map(searchHistoryDao.getSearchHistories()) {
            itemSearchEntityMapper.mapToListDomain(it)
        }

    override fun insertHistory(search: Search) {
        val entity = itemSearchEntityMapper.mapToModel(search)
        executor.execute { searchHistoryDao.insertHistory(entity) }
    }

    override fun deleteAllHistories() {
        executor.execute { searchHistoryDao.deleteAllHistories() }
    }
}