package com.masuwes.core.data.mapper

import com.masuwes.core.data.model.search.SearchItem
import com.masuwes.core.domain.model.Search

class SearchMapper : BaseMapper<SearchItem, Search> {
    override fun mapToDomain(model: SearchItem): Search {
        return Search(
            model.id,
            model.media_type,
            model.name,
            model.poster_path,
            model.title,
            model.original_language,
            model.first_air_date,
            model.release_date,
            model.vote_average
        )
    }

    override fun mapToModel(domain: Search): SearchItem {
        return SearchItem(
            domain.id,
            domain.media_type,
            domain.name,
            domain.poster_path,
            domain.title,
            domain.original_language,
            domain.first_air_date,
            domain.release_date,
            domain.vote_average
        )
    }
}