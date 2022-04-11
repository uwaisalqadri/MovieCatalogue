package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem

interface DetailRepository {
    suspend fun getDetailMovie(movieId: Int): DetailMovieItem

    fun getFavoriteMovieById(idMovie: Int): List<DetailMovieItem>

    suspend fun insertFavoriteMovie(movies: DetailMovieItem)

    suspend fun deleteFavoriteMovie(idMovie: Int)

    suspend fun getDetailTvShow(tvId: Int): DetailTvShowItem

    fun getFavoriteTvById(idTv: Int): List<DetailTvShowItem>

    suspend fun insertFavoriteTv(tvShow: DetailTvShowItem)

    suspend fun deleteFavoriteTv(idTv: Int)

}