package com.masuwes.moviecatalogue

import android.app.Application
import com.facebook.stetho.Stetho
import com.masuwes.moviecatalogue.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    mapperModule,
                    useCaseModule,
                    utilsModule,
                    viewModelModule
                )
            )
        }
    }
}