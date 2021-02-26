package com.masuwes.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masuwes.core.data.local.dao.MoviesDao
import com.masuwes.core.data.local.dao.TvShowsDao
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow

@Database(
    entities = [
        DetailMovie::class,
        DetailTvShow::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao
    abstract fun tvShowsDao() : TvShowsDao
}
