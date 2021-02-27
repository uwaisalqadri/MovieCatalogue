package com.masuwes.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.masuwes.core.domain.model.Search
import io.reactivex.Single
import retrofit2.http.Query

interface SearchRepository {

    fun searchAll(
        api_key: String,
        language: String,
        query: String,
        page: Int
    ) : Single<List<Search>>

    fun getSearchHistories(): LiveData<List<Search>>

    fun insertHistory(search: Search)

    fun deleteAllHistories()
}