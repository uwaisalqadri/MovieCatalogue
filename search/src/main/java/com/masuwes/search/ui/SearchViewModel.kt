package com.masuwes.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.masuwes.core.data.repository.SearchRepositoryImpl
import com.masuwes.core.data.source.local.SearchHistoryDao
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.usecase.search.SearchUseCase
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.RxUtils
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

class SearchViewModel(
    private val searchUseCase: SearchUseCase
    ) : BaseViewModel() {

    val postsData = MutableLiveData<SearchState>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()
    val getSearchHistories = searchUseCase.getSearchHistories()

    fun searchAll(query: String, page: Int) {
        showProgressbar.value = true
        compositeDisposable.add(
            searchUseCase.searchAll(Constants.API_KEY, Constants.LANG, query, page)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postsData.value = SearchDataLoaded(result)
                        showProgressbar.value = false
                    } else {
                        if (page == 1) postsData.value = DataNotFoundState
                    }
                }, this::onError)
        )
    }

    fun insertHistory(search: Search) {
        searchUseCase.insertHistory(search)
    }

    fun deleteAllHistories() {
        searchUseCase.deleteAllHistories()
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message.toString()
    }
}


sealed class SearchState
data class SearchDataLoaded(val search: List<Search>) : SearchState()
object DataNotFoundState : SearchState()


















