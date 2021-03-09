package com.masuwes.core.data.mapper.entity

import com.masuwes.core.data.mapper.BaseMapper
import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import com.masuwes.core.domain.model.DetailTvShow

class DetailTvShowEntityMapper : BaseMapper<DetailTvShowEntity, DetailTvShow> {
    override fun mapToDomain(model: DetailTvShowEntity): DetailTvShow {
        return DetailTvShow(
                model.backdrop_path,
                model.first_air_date,
                model.id,
                model.name,
                model.original_language,
                model.original_name,
                model.overview,
                model.popularity,
                model.poster_path,
                model.vote_average,
                model.vote_count
        )
    }

    override fun mapToModel(domain: DetailTvShow): DetailTvShowEntity {
        return DetailTvShowEntity(
                domain.backdrop_path,
                domain.first_air_date,
                domain.id,
                domain.name,
                domain.original_language,
                domain.original_name,
                domain.overview,
                domain.popularity,
                domain.poster_path,
                domain.vote_average,
                domain.vote_count
        )
    }
}