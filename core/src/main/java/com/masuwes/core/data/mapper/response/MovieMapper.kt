package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.model.movie.MovieItem
import com.masuwes.core.domain.model.Movie

fun List<MovieItem>.map(): List<Movie> {
    return map { it.map() }
}

fun MovieItem.map(): Movie {
    return Movie(
        id = id ?: 0,
        adult = adult ?: false,
        backdropPath = backdrop_path.orEmpty(),
        genreIds = genre_ids.orEmpty(),
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

fun Movie.map(): MovieItem {
    return MovieItem(
        id = id,
        adult = adult,
        backdrop_path = backdropPath,
        genre_ids = genreIds,
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