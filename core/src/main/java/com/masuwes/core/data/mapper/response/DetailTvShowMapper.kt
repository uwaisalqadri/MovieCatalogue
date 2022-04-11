package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import com.masuwes.core.data.model.response.detail.DetailTvShowItem
import com.masuwes.core.domain.model.DetailTvShow

fun List<DetailTvShowEntity>.map(): List<DetailTvShow> {
    return map { it.map() }
}

fun DetailTvShowItem.map(): DetailTvShow {
    return DetailTvShow(
        backdrop_path = backdrop_path.orEmpty(),
        firstAirDate = first_air_date.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        originalName = original_name.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = poster_path.orEmpty(),
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0
    )
}

fun DetailTvShow.map(): DetailTvShowItem {
    return DetailTvShowItem(
        backdrop_path = backdrop_path,
        first_air_date = firstAirDate,
        id = id,
        name = name,
        original_language = originalLanguage,
        original_name = originalName,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}

fun DetailTvShowEntity.map(): DetailTvShow {
    return DetailTvShow(
        backdrop_path = backdrop_path.orEmpty(),
        firstAirDate = first_air_date.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        originalName = original_name.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = poster_path.orEmpty(),
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0
    )
}

fun DetailTvShow.mapEntity(): DetailTvShowEntity {
    return DetailTvShowEntity(
        backdrop_path = backdrop_path,
        first_air_date = firstAirDate,
        id = id,
        name = name,
        original_language = originalLanguage,
        original_name = originalName,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}