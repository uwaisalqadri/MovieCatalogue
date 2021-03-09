package com.masuwes.core.data.mapper.entity

import com.masuwes.core.data.mapper.BaseMapper
import com.masuwes.core.data.model.entity.search.SearchEntity
import com.masuwes.core.domain.model.Search

class SearchEntityMapper : BaseMapper<SearchEntity, Search> {
    override fun mapToDomain(model: SearchEntity): Search {
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

    override fun mapToModel(domain: Search): SearchEntity {
        return SearchEntity(
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