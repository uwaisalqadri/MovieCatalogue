package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.mapper.BaseMapper
import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.domain.model.DetailMovie

class DetailMovieMapper : BaseMapper<DetailMovieItem, DetailMovie> {
    override fun mapToDomain(model: DetailMovieItem): DetailMovie {
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

    override fun mapToModel(domain: DetailMovie): DetailMovieItem {
        return DetailMovieItem(
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