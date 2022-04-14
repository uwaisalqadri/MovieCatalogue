package com.masuwes.core.data.source.remote

import com.masuwes.core.data.base.BaseResponse
import com.masuwes.core.data.model.detail.response.DetailMovieItem
import com.masuwes.core.data.model.detail.response.DetailTvShowItem
import com.masuwes.core.data.model.movie.MovieItem
import com.masuwes.core.data.model.movie.MovieResponse
import com.masuwes.core.data.model.search.response.SearchItem
import com.masuwes.core.data.model.search.response.SearchResponse
import com.masuwes.core.data.model.tvshow.TvShowItem
import com.masuwes.core.data.model.tvshow.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): BaseResponse<List<MovieItem>>

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): BaseResponse<List<TvShowItem>>

    @GET("search/multi")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("page") page: Int
    ): BaseResponse<List<SearchItem>>

    // detail
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): DetailMovieItem

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvId: Int
    ): DetailTvShowItem
}