package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.model.entity.detail.DetailMovieEntity
import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.domain.model.DetailMovie

fun List<DetailMovieEntity>.map(): List<DetailMovie> {
    return map { it.map() }
}

fun DetailMovieItem.map(): DetailMovie {
    return DetailMovie(
        adult = adult ?: false,
        backdropPath = backdrop_path.orEmpty(),
        id = id ?: 0,
        originalLanguage = original_language.orEmpty(),
        originalTitle = original_title.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = poster_path.orEmpty(),
        releaseDate = release_date.orEmpty(),
        title = title.orEmpty(),
        hasVideo = video ?: false,
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0
    )
}

fun DetailMovie.map(): DetailMovieItem {
    return DetailMovieItem(
        adult = adult,
        backdrop_path = backdropPath,
        id = id,
        original_language = originalLanguage,
        original_title = originalTitle,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        release_date = releaseDate,
        title = title,
        video = hasVideo,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}

fun DetailMovieEntity.map(): DetailMovie {
    return DetailMovie(
        adult = adult ?: false,
        backdropPath = backdrop_path.orEmpty(),
        id = id ?: 0,
        originalLanguage = original_language.orEmpty(),
        originalTitle = original_title.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = poster_path.orEmpty(),
        releaseDate = release_date.orEmpty(),
        title = title.orEmpty(),
        hasVideo = video ?: false,
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0
    )
}

fun DetailMovie.mapEntity(): DetailMovieEntity {
    return DetailMovieEntity(
        adult = adult,
        backdrop_path = backdropPath,
        id = id,
        original_language = originalLanguage,
        original_title = originalTitle,
        overview = overview,
        popularity = popularity,
        poster_path = posterPath,
        release_date = releaseDate,
        title = title,
        video = hasVideo,
        vote_average = voteAverage,
        vote_count = voteCount
    )
}