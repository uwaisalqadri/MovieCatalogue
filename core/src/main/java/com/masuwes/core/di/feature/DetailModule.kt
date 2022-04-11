package com.masuwes.core.di.feature

import com.masuwes.core.data.repository.DetailDataStore
import com.masuwes.core.domain.repository.DetailRepository
import com.masuwes.core.domain.usecase.detail.DetailInteractor
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import org.koin.dsl.module

val detailModule = module {
    single<DetailRepository> { DetailDataStore(get(), get(), get(), get()) }
    single<DetailUseCase> { DetailInteractor(get()) }
}