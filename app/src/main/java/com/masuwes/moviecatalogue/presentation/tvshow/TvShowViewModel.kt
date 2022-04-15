package com.masuwes.moviecatalogue.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.utils.ui.Result
import com.masuwes.moviecatalogue.utils.ui.collectFlow
import kotlinx.coroutines.launch

class TvShowViewModel(
    private val tvShowUseCase: TvShowUseCase
): ViewModel() {

    private val _tvShowsResult = MutableLiveData<Result<List<TvShow>>>()
    val tvShowsResult: LiveData<Result<List<TvShow>>> get() = _tvShowsResult

    init {
        _tvShowsResult.value = Result.default()
    }

    fun getTvShows(page: Int) = viewModelScope.launch {
        _tvShowsResult.value = Result.loading()
        collectFlow(_tvShowsResult) {
            tvShowUseCase.getTvShows("", page)
        }
    }
}