package com.masuwes.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masuwes.core.data.model.entity.detail.DetailMovieEntity
import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import com.masuwes.core.data.model.entity.search.SearchEntity

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
