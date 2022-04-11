package com.masuwes.core.data.source.remote

import com.masuwes.core.data.model.response.detail.DetailMovieItem
import com.masuwes.core.data.model.response.detail.DetailTvShowItem
import com.masuwes.core.data.model.response.movie.MovieResponse
import com.masuwes.core.data.model.response.search.SearchResponse
import com.masuwes.core.data.model.response.tvshow.TvShowResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int
    ): TvShowResponse

    @GET("search/multi")
    fun getSearch(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchResponse

    // detail
    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): DetailMovieItem

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvId: Int
    ): DetailTvShowItem
}