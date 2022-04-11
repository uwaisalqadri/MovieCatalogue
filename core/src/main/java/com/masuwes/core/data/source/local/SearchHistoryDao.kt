package com.masuwes.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.data.model.entity.search.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(search: SearchEntity)

    @Query("SELECT * FROM search_history ORDER BY id ASC")
    fun getSearchHistories(): List<SearchEntity>

    @Query("DELETE FROM search_history")
    suspend fun deleteAllHistories()

}