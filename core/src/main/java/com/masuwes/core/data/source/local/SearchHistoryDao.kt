package com.masuwes.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.masuwes.core.domain.model.Search
import io.reactivex.Single

@Dao
interface SearchHistoryDao {

    @Insert
    fun insertHistory(search: Search)

    @Query("SELECT * FROM search_history ORDER BY id ASC")
    fun getSearchHistories(): Single<List<Search>>

    @Query("DELETE FROM search_history")
    fun deleteAllHistories()

}