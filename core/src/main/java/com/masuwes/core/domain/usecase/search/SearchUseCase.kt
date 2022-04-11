package com.masuwes.core.domain.usecase.search

import androidx.lifecycle.LiveData
import com.masuwes.core.domain.model.Search
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    fun getSearch(query: String, page: Int): Flow<List<Search>>

//    fun getSearchHistories(): LiveData<List<Search>>
//
//    fun insertHistory(search: Search)
//
//    fun deleteAllHistories()
}