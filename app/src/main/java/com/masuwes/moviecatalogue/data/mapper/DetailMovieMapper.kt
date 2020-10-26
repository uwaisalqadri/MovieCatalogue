package com.masuwes.moviecatalogue.data.mapper

import com.masuwes.moviecatalogue.data.model.detail.DetailMovieItem
import com.masuwes.moviecatalogue.domain.model.DetailMovie

class DetailMovieMapper : BaseMapper<DetailMovieItem, DetailMovie> {
    override fun mapToDomain(model: DetailMovieItem): DetailMovie {
        return DetailMovie(
            model.adult,
            model.backdrop_path,
            model.genre_ids,
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
            domain.genre_ids,
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