package com.masuwes.core.data.repository

import com.masuwes.core.data.mapper.SearchMapper
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.repository.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(
    private val apiService: ApiService,
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
}