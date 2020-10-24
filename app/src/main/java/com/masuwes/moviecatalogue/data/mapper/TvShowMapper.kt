package com.masuwes.moviecatalogue.data.mapper

import com.masuwes.moviecatalogue.data.model.tvshow.TvShowItem
import com.masuwes.moviecatalogue.domain.TvShow

class TvShowMapper : BaseMapper<TvShowItem, TvShow> {
    override fun mapToDomain(model: TvShowItem): TvShow {
        return TvShow(
            model.backdrop_path,
            model.first_air_date,
            model.genre_ids,
            model.id,
            model.name,
            model.origin_country,
            model.original_language,
            model.original_name,
            model.overview,
            model.popularity,
            model.poster_path,
            model.vote_average,
            model.vote_count
        )
    }

    override fun mapToModel(domain: TvShow): TvShowItem {
        return TvShowItem(
            domain.backdrop_path,
            domain.first_air_date,
            domain.genre_ids,
            domain.id,
            domain.name,
            domain.origin_country,
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