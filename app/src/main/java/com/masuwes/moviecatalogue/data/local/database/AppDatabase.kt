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


abstract class AppDatabase : RoomDatabase() {

}