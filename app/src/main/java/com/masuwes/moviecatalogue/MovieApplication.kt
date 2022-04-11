package com.masuwes.moviecatalogue

import android.app.Application
import com.masuwes.core.BuildConfig
import com.masuwes.core.di.*
import com.masuwes.core.di.feature.detailModule
import com.masuwes.core.di.feature.movieModule
import com.masuwes.core.di.feature.searchModule
import com.masuwes.core.di.feature.tvShowModule
import com.masuwes.moviecatalogue.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                databaseModule,
                networkModule,
                detailModule,
                movieModule,
                searchModule,
                tvShowModule,
                featureModule
            )
        }
    }

}