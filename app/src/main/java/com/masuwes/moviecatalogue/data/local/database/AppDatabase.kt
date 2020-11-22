package com.masuwes.moviecatalogue.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masuwes.moviecatalogue.data.local.dao.MoviesDao
import com.masuwes.moviecatalogue.data.local.dao.TvShowsDao
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.utils.Constants

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

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        Constants.DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
