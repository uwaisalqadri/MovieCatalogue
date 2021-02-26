package com.masuwes.moviecatalogue

import android.app.Application
import com.masuwes.core.di.*
import com.masuwes.moviecatalogue.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    mapperModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}