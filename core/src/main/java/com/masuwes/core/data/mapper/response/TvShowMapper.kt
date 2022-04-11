package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.model.response.tvshow.TvShowItem
import com.masuwes.core.domain.model.TvShow

fun List<TvShowItem>.map(): List<TvShow> {
    return map { it.map() }
}

fun TvShowItem.map(): TvShow {
    return TvShow(
        id = id ?: 0,
        backdrop_path = backdrop_path.orEmpty(),
        firstAirDate = first_air_date.orEmpty(),
        genreIds = genre_ids.orEmpty(),
        name = name.orEmpty(),
        originCountry = origin_country.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        originalName = original_name.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = poster_path.orEmpty(),
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0
    )
}

fun TvShow.map(): TvShowItem {
    return TvShowItem(
        id = id,
        backdrop_path = backdrop_path,
        first_air_date = firstAirDate,
        genre_ids = genreIds,
        name = name,
        origin_country = originCountry,
        original_language = originalLanguage,
        original_name = originalName,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}