package com.masuwes.core.domain.usecase.detail

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {

    suspend fun getDetailMovie(movieId: Int) : Flow<DetailMovie>

    suspend fun getFavoriteMovieById(movieId: Int): Flow<List<DetailMovie>>

    suspend fun insertFavoriteMovie(movie: DetailMovie)

    suspend fun deleteFavoriteMovie(movieId: Int)

    suspend fun getDetailTvShow(tvId: Int): Flow<DetailTvShow>

    suspend fun getFavoriteTvById(tvId: Int): Flow<List<DetailTvShow>>

    suspend fun insertFavoriteTv(tvShow: DetailTvShow)

    suspend fun deleteFavoriteTv(tvId: Int)
}