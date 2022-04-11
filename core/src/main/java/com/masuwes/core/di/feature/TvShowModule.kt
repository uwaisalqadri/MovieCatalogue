package com.masuwes.core.di.feature

import com.masuwes.core.data.repository.TvShowDataStore
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.domain.usecase.tvshow.TvShowInteractor
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import org.koin.dsl.module

val tvShowModule = module {
    single<TvShowRepository> { TvShowDataStore(get(), get()) }
    single<TvShowUseCase> { TvShowInteractor(get()) }
}