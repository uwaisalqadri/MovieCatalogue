package com.masuwes.search.ui

import androidx.lifecycle.MutableLiveData
import com.masuwes.core.domain.model.Search
import com.masuwes.core.domain.usecase.search.SearchUseCase
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.ui.BaseViewModel
import com.masuwes.moviecatalogue.utils.RxUtils

class SearchViewModel(private val searchUseCase: SearchUseCase) : BaseViewModel() {

    val postsData = MutableLiveData<SearchState>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun searchAll(query: String, page: Int) {
        postsData.value = LoadingState
        compositeDisposable.add(
            searchUseCase.searchAll(Constants.API_KEY, Constants.LANG, query, page)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        postsData.value = SearchDataLoaded(result)
                        showProgressbar.value = false
                    } else {
                        if (page == 1) postsData.value = DataNotFoundState
                        else postsData.value = LastPageState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message.toString()
    }
}


sealed class SearchState
data class SearchDataLoaded(val search: List<Search>) : SearchState()
object LoadingState : SearchState()
object LastPageState : SearchState()
object DataNotFoundState : SearchState()


















