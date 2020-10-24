package com.masuwes.moviecatalogue.data.mapper

import com.masuwes.moviecatalogue.data.model.movie.MovieItem
import com.masuwes.moviecatalogue.domain.Movie

class MovieMapper : BaseMapper<MovieItem, Movie> {
    override fun mapToDomain(model: MovieItem): Movie {
        return Movie(
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

    override fun mapToModel(domain: Movie): MovieItem {
        return MovieItem(
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