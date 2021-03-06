package com.masuwes.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.domain.model.Search

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(search: Search)

    @Query("SELECT * FROM search_history ORDER BY id ASC")
    fun getSearchHistories(): LiveData<List<Search>>

    @Query("DELETE FROM search_history")
    fun deleteAllHistories()

}