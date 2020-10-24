package com.masuwes.moviecatalogue.di

import android.graphics.Movie
import com.masuwes.moviecatalogue.data.service.LoggingInterceptor
import org.koin.dsl.module

val appModule = module {

    single { LoggingInterceptor() }
}

val dataModule = module {

    // db dao

    // repository

}