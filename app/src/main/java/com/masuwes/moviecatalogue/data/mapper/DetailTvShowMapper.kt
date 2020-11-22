package com.masuwes.moviecatalogue.data.mapper

import com.masuwes.moviecatalogue.data.model.detail.DetailTvShowItem
import com.masuwes.moviecatalogue.domain.model.DetailTvShow

class DetailTvShowMapper : BaseMapper<DetailTvShowItem, DetailTvShow> {
    override fun mapToDomain(model: DetailTvShowItem): DetailTvShow {
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

    override fun mapToModel(domain: DetailTvShow): DetailTvShowItem {
        return DetailTvShowItem(
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