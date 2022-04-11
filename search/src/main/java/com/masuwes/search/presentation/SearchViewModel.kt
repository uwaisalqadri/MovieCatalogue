package com.masuwes.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.usecase.search.SearchUseCase
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch
import com.masuwes.moviecatalogue.utils.ui.Result as Result

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _searchResult = MutableLiveData<Result<List<Search>>>()
    val searchResult: LiveData<Result<List<Search>>> get() = _searchResult

    init {
        _searchResult.value = Result.default()
    }

    fun getSearch(query: String, page: Int) = viewModelScope.launch {
        _searchResult.value = Result.loading()
        collectFlow(_searchResult) {
            searchUseCase.getSearch(query, page)
        }
    }

    fun getSearchHistories() {

    }

    fun insertHistory(search: Search) = viewModelScope.launch {
    }

    fun deleteAllHistories() {
    }
}


sealed class SearchState
data class SearchDataLoaded(val search: List<Search>) : SearchState()
object DataNotFoundState : SearchState()


















