package com.masuwes.core.data.source.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowsDao {
    @Query("SELECT * FROM tb_detail_tvshow ORDER BY id ASC")
    fun getFavoriteTv(): Flow<List<DetailTvShowEntity>>

    @Query("SELECT * FROM tb_detail_tvshow WHERE id=:idTv")
    fun getFavoriteTvById(idTv: Int): Flow<List<DetailTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteTv(tvShow: DetailTvShowEntity)

    @Query("DELETE FROM tb_detail_tvshow WHERE id =:idTv ")
    suspend fun deleteFavoriteTv(idTv: Int)

//    @Query("SELECT * FROM tb_detail_tvshow ORDER BY  id ASC")
//    fun getFavTvPagination(): DataSource.Factory<Int, DetailTvShowEntity>
}