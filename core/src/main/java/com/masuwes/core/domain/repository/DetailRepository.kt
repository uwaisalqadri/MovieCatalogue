package com.masuwes.core.domain.repository

import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem

interface DetailRepository {
    fun getDetailMovie(movieId: Int): DetailMovieItem

    fun getFavoriteMovieById(idMovie: Int): List<DetailMovieItem>

    fun insertFavoriteMovie(movies: DetailMovieItem)

    fun deleteFavoriteMovie(idMovie: Int)

    fun getDetailTvShow(tvId: Int): DetailTvShowItem

    fun getFavoriteTvById(idTv: Int): List<DetailTvShowItem>

    fun insertFavoriteTv(tvShow: DetailTvShowItem)

    fun deleteFavoriteTv(idTv: Int)

}