package com.masuwes.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masuwes.core.data.model.entity.detail.DetailMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
    fun getFavoriteMovie(): List<DetailMovieEntity>

    @Query("SELECT * FROM tb_detail_movie WHERE id=:movieId")
    fun getFavoriteMovieById(movieId: Int): List<DetailMovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(movies: DetailMovieEntity)

    @Query("DELETE FROM tb_detail_movie WHERE id=:movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)

//    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
//    fun getFavMoviePagination(): DataSource.Factory<Int, DetailMovieEntity>
}