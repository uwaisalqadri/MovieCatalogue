package com.masuwes.core.domain.usecase.search

import androidx.lifecycle.LiveData
import com.masuwes.core.data.model.entity.search.SearchEntity
import com.masuwes.core.domain.model.Search
import io.reactivex.Single

interface SearchUseCase {

    fun searchAll(
        api_key: String,
        language: String,
        query: String,
        page: Int
    ) : Single<List<Search>>

    fun getSearchHistories(): LiveData<List<Search>>

    fun insertHistory(search: Search)

    fun deleteAllHistories()

    fun mappingToObject(result: List<SearchEntity>) : List<Search>

}