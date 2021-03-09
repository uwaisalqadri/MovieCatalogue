package com.masuwes.core.domain.repository

import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import io.reactivex.Single

interface DetailRepository {

    fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String,
    ) : Single<DetailMovie>

//    fun getFavoriteMovieById(idMovie: Int): Single<List<DetailMovieEntity>>
//
//    fun insertFavoriteMovie(movies: DetailMovie)
//
//    fun deleteFavoriteMovie(idMovie: Int)
//
//    fun mappingMovieToObject(result : List<DetailMovieEntity>) : List<DetailMovie>

    fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String,
    ) : Single<DetailTvShow>

//    fun getFavoriteTvById(idTv: Int): Single<List<DetailTvShowEntity>>
//
//    fun insertFavoriteTv(tvShow: DetailTvShow)
//
//    fun deleteFavoriteTv(idTv: Int)
//
//    fun mappingTvShowToObject(result : List<DetailTvShowEntity>) : List<DetailTvShow>

}