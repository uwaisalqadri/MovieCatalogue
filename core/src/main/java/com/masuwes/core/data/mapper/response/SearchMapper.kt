package com.masuwes.core.data.mapper.response

import com.masuwes.core.data.model.search.entity.SearchEntity
import com.masuwes.core.data.model.search.response.SearchItem
import com.masuwes.core.domain.model.Search

@JvmName("mapSearchEntity")
fun List<SearchEntity>.map(): List<Search> {
    return map { it.map() }
}

fun List<SearchItem>.map(): List<Search> {
    return map { it.map() }
}

fun SearchItem.map(): Search {
    return Search(
        id = id,
        mediaType = media_type.orEmpty(),
        name = name.orEmpty(),
        posterPath = poster_path.orEmpty(),
        title = title.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        firstAirDate = first_air_date.orEmpty(),
        releaseDate = release_date.orEmpty(),
        voteAverage = vote_average ?: 0.0
    )
}

fun Search.map(): SearchItem {
    return SearchItem(
        id = id,
        media_type = mediaType,
        name = name,
        poster_path = posterPath,
        title = title,
        original_language = originalLanguage,
        first_air_date = firstAirDate,
        release_date = releaseDate,
        vote_average = voteAverage
    )
}

fun SearchEntity.map(): Search {
    return Search(
        id = id,
        mediaType = media_type.orEmpty(),
        name = name.orEmpty(),
        posterPath = poster_path.orEmpty(),
        title = title.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        firstAirDate = first_air_date.orEmpty(),
        releaseDate = release_date.orEmpty(),
        voteAverage = vote_average ?: 0.0
    )
}

fun Search.mapEntity(): SearchEntity {
    return SearchEntity(
        id = id,
        media_type = mediaType,
        name = name,
        poster_path = posterPath,
        title = title,
        original_language = originalLanguage,
        first_air_date = firstAirDate,
        release_date = releaseDate,
        vote_average = voteAverage
    )
}