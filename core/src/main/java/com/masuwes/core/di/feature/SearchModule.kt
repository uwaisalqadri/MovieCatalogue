package com.masuwes.core.di.feature

import com.masuwes.core.data.repository.SearchDataStore
import com.masuwes.core.domain.repository.SearchRepository
import com.masuwes.core.domain.usecase.search.SearchInteractor
import com.masuwes.core.domain.usecase.search.SearchUseCase
import org.koin.dsl.module

val searchModule = module {
    single<SearchRepository> { SearchDataStore(get(), get(), get()) }
    single<SearchUseCase> { SearchInteractor(get()) }
}