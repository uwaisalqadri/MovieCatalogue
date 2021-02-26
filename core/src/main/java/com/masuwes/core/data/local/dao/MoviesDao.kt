package com.masuwes.core.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.domain.model.DetailMovie
import io.reactivex.Single

@Dao
interface MoviesDao {
    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
    fun getFavoriteMovie(): Single<List<DetailMovie>>

    @Query("SELECT * FROM tb_detail_movie WHERE id=:idMovie")
    fun getFavoriteMovieById(idMovie: Int): Single<List<DetailMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movies: DetailMovie)

    @Query("DELETE FROM tb_detail_movie WHERE id=:idMovie")
    fun deleteFavoriteMovie(idMovie: Int)

    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
    fun getFavMoviePagination(): DataSource.Factory<Int, DetailMovie>
}