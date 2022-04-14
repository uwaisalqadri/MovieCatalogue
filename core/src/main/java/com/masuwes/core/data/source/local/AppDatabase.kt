package com.masuwes.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masuwes.core.data.model.detail.entity.DetailMovieEntity
import com.masuwes.core.data.model.detail.entity.DetailTvShowEntity
import com.masuwes.core.data.model.search.entity.SearchEntity

@Database(
    entities = [
        DetailMovieEntity::class,
        DetailTvShowEntity::class,
        SearchEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao
    abstract fun tvShowsDao() : TvShowsDao
    abstract fun searchHistoryDao() : SearchHistoryDao
}
