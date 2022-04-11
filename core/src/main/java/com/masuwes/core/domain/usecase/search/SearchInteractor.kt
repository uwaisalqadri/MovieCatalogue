package com.masuwes.core.domain.usecase.search

import com.masuwes.core.data.mapper.response.map
import com.masuwes.core.data.mapper.response.mapEntity
import com.masuwes.core.domain.base.execute
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchInteractor(private val searchRepository: SearchRepository) : SearchUseCase {

    override fun getSearch(query: String, page: Int): Flow<List<Search>> {
        return execute {
            searchRepository.getSearch(query, page).map()
        }
    }

    override fun getSearchHistories(): Flow<List<Search>> {
        return execute {
            searchRepository.getSearchHistories().map()
        }
    }

    override suspend fun insertHistory(search: Search) {
        searchRepository.insertHistory(search.mapEntity())
    }

    override suspend fun deleteAllHistories() {
        searchRepository.deleteAllHistories()
    }

}