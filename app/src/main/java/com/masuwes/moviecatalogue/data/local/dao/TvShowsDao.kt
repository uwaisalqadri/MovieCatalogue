package com.masuwes.moviecatalogue.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import io.reactivex.Single

@Dao
interface TvShowsDao {
    @Query("SELECT * FROM tb_detail_tvshow ORDER BY id ASC")
    fun getFavoriteTv(): Single<List<DetailTvShow>>

    @Query("SELECT * FROM tb_detail_tvshow WHERE id=:idTv")
    fun getFavoriteTvById(idTv: Int): Single<List<DetailTvShow>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteTv(tvShow: DetailTvShow)

    @Query("DELETE FROM tb_detail_tvshow WHERE id =:idTv ")
    fun deleteFavoriteTv(idTv: Int)

    @Query("SELECT * FROM tb_detail_tvshow ORDER BY  id ASC")
    fun getFavTvPagination(): DataSource.Factory<Int, DetailTvShow>
}