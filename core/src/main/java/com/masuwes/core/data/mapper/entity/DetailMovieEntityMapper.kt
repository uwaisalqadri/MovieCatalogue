package com.masuwes.core.data.mapper.entity

import com.masuwes.core.data.mapper.BaseMapper
import com.masuwes.core.data.model.entity.detail.DetailMovieEntity
import com.masuwes.core.domain.model.DetailMovie

class DetailMovieEntityMapper : BaseMapper<DetailMovieEntity, DetailMovie> {
    override fun mapToDomain(model: DetailMovieEntity): DetailMovie {
        return DetailMovie(
                model.adult,
                model.backdrop_path,
                model.id,
                model.original_language,
                model.original_title,
                model.overview,
                model.popularity,
                model.poster_path,
                model.release_date,
                model.title,
                model.video,
                model.vote_average,
                model.vote_count
        )
    }

    override fun mapToModel(domain: DetailMovie): DetailMovieEntity {
        return DetailMovieEntity(
                domain.adult,
                domain.backdrop_path,
                domain.id,
                domain.original_language,
                domain.original_title,
                domain.overview,
                domain.popularity,
                domain.poster_path,
                domain.release_date,
                domain.title,
                domain.video,
                domain.vote_average,
                domain.vote_count
        )
    }
}