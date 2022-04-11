package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.entity.detail.DetailMovieEntity
import com.masuwes.core.data.model.entity.detail.DetailTvShowEntity
import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem

interface DetailRepository {
    suspend fun getDetailMovie(movieId: Int): DetailMovieItem

    fun getFavoriteMovieById(movieId: Int): List<DetailMovieEntity>

    suspend fun insertFavoriteMovie(movie: DetailMovieEntity)

    suspend fun deleteFavoriteMovie(movieId: Int)

    suspend fun getDetailTvShow(tvId: Int): DetailTvShowItem

    fun getFavoriteTvById(tvId: Int): List<DetailTvShowEntity>

    suspend fun insertFavoriteTv(tvShow: DetailTvShowEntity)

    suspend fun deleteFavoriteTv(tvId: Int)

}