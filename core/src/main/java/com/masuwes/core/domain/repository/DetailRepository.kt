package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.detail.entity.DetailMovieEntity
import com.masuwes.core.data.model.detail.entity.DetailTvShowEntity
import com.masuwes.core.data.model.detail.response.DetailMovieItem
import com.masuwes.core.data.model.detail.response.DetailTvShowItem

interface DetailRepository {
    suspend fun getDetailMovie(movieId: Int): DetailMovieItem

    suspend fun getFavoriteMovieById(movieId: Int): List<DetailMovieEntity>

    suspend fun insertFavoriteMovie(movie: DetailMovieEntity)

    suspend fun deleteFavoriteMovie(movieId: Int)

    suspend fun getDetailTvShow(tvId: Int): DetailTvShowItem

    suspend fun getFavoriteTvById(tvId: Int): List<DetailTvShowEntity>

    suspend fun insertFavoriteTv(tvShow: DetailTvShowEntity)

    suspend fun deleteFavoriteTv(tvId: Int)

}