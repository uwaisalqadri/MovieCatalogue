package com.masuwes.moviecatalogue.data.service

import com.masuwes.moviecatalogue.data.model.detail.DetailMovieItem
import com.masuwes.moviecatalogue.data.model.detail.DetailTvShowItem
import com.masuwes.moviecatalogue.data.model.movie.ResponseMovie
import com.masuwes.moviecatalogue.data.model.tvshow.ResponseTvShow
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ) : Single<ResponseMovie>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ) : Single<ResponseTvShow>

    // detail
    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Query("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : Single<DetailMovieItem>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Query("tv_id") tv_id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : Single<DetailTvShowItem>
}




















