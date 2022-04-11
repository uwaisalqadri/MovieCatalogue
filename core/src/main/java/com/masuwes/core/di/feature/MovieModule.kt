package com.masuwes.core.di.feature

import com.masuwes.core.data.repository.MovieDataStore
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.domain.usecase.movie.MovieInteractor
import com.masuwes.core.domain.usecase.movie.MovieUseCase
import org.koin.dsl.module

val movieModule = module {
    single<MovieRepository> { MovieDataStore(get(), get()) }
    single<MovieUseCase> { MovieInteractor(get()) }
}