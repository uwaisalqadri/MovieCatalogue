package com.masuwes.moviecatalogue.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _getFragmentKey = MutableLiveData<Int>()
    val getFragmentKey: LiveData<Int> get() = _getFragmentKey

    fun setFragmentKey(key: Int) {
        viewModelScope.launch {
            _getFragmentKey.postValue(key)
        }
    }

}