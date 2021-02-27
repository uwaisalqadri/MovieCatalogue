package com.masuwes.core.domain.usecase.search

import androidx.lifecycle.LiveData
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.repository.SearchRepository
import io.reactivex.Single

class SearchInteractor(private val searchRepository: SearchRepository) : SearchUseCase {
    override fun searchAll(
        api_key: String,
        language: String,
        query: String,
        page: Int
    ): Single<List<Search>> =
        searchRepository.searchAll(api_key, language, query, page)

    override fun getSearchHistories(): LiveData<List<Search>> =
        searchRepository.getSearchHistories()

    override fun insertHistory(search: Search) {
        searchRepository.insertHistory(search)
    }

    override fun deleteAllHistories() {
        searchRepository.deleteAllHistories()
    }

}