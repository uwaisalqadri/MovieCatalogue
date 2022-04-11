package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {

    fun getDetailMovie(movieId: Int) : Flow<DetailMovie>

//    fun getFavoriteMovieById(idMovie: Int): Flow<List<DetailMovie>>
//
//    fun insertFavoriteMovie(movies: DetailMovie)
//
//    fun deleteFavoriteMovie(idMovie: Int)

    fun getDetailTvShow(tvId: Int): Flow<DetailTvShow>

//    fun getFavoriteTvById(idTv: Int): Flow<List<DetailTvShow>>
//
//    fun insertFavoriteTv(tvShow: DetailTvShow)
//
//    fun deleteFavoriteTv(idTv: Int)
}