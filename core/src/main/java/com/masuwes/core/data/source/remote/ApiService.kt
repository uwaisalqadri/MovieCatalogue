package com.masuwes.core.data.source.remote

import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem
import com.masuwes.core.data.model.response.movie.ResponseMovie
import com.masuwes.core.data.model.response.search.ResponseSearch
import com.masuwes.core.data.model.response.tvshow.ResponseTvShow
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ) : Single<ResponseMovie>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ) : Single<ResponseTvShow>

    @GET("search/multi")
    fun searchAll(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ) : Single<ResponseSearch>

    // detail
    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : Single<DetailMovieItem>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : Single<DetailTvShowItem>
}




















