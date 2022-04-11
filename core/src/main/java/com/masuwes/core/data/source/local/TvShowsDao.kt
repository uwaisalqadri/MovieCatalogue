package com.masuwes.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowsDao {
    @Query("SELECT * FROM tb_detail_tvshow ORDER BY id ASC")
    fun getFavoriteTv(): List<DetailTvShowEntity>

    @Query("SELECT * FROM tb_detail_tvshow WHERE id=:tvId")
    fun getFavoriteTvById(tvId: Int): List<DetailTvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteTv(tvShow: DetailTvShowEntity)

    @Query("DELETE FROM tb_detail_tvshow WHERE id =:tvId ")
    suspend fun deleteFavoriteTv(tvId: Int)

//    @Query("SELECT * FROM tb_detail_tvshow ORDER BY  id ASC")
//    fun getFavTvPagination(): DataSource.Factory<Int, DetailTvShowEntity>
}