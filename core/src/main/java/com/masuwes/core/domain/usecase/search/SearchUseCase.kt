package com.masuwes.core.domain.usecase.search

import com.masuwes.core.domain.model.Search
import io.reactivex.Single

interface SearchUseCase {

    fun searchAll(
        api_key: String,
        language: String,
        query: String,
        page: Int
    ) : Single<List<Search>>
}