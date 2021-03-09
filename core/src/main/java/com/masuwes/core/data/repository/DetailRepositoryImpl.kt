package com.masuwes.core.data.repository

import com.masuwes.core.data.mapper.entity.DetailMovieEntityMapper
import com.masuwes.core.data.mapper.entity.DetailTvShowEntityMapper
import com.masuwes.core.data.mapper.response.DetailMovieMapper
import com.masuwes.core.data.mapper.response.DetailTvShowMapper
import com.masuwes.core.data.source.local.MoviesDao
import com.masuwes.core.data.source.local.TvShowsDao
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.repository.DetailRepository
import io.reactivex.Single
import java.util.concurrent.Executor

class DetailRepositoryImpl(
        private val apiService: ApiService,
        private val moviesDao: MoviesDao,
        private val tvShowsDao: TvShowsDao,
        private val executor: Executor,
        private val itemDetailMovieMapper: DetailMovieMapper,
        private val itemDetailTvShowMapper: DetailTvShowMapper,
        private val itemDetailMovieEntityMapper: DetailMovieEntityMapper,
        private val itemDetailTvShowEntityMapper: DetailTvShowEntityMapper
) : DetailRepository {
    override fun getDetailMovie(
        movie_id: Int,
        api_key: String,
        language: String
    ): Single<DetailMovie> {
        return apiService.getMovieDetail(movie_id, api_key, language).map {
            itemDetailMovieMapper.mapToDomain(it)
        }
    }

    override fun getFavoriteMovieById(idMovie: Int): Single<List<DetailMovie>> =
        moviesDao.getFavoriteMovieById(idMovie).map {
            itemDetailMovieEntityMapper.mapToListDomain(it)
        }

    override fun insertFavoriteMovie(movies: DetailMovie) {
        val entity = itemDetailMovieEntityMapper.mapToModel(movies)
        executor.execute { moviesDao.insertFavoriteMovie(entity) }
    }

    override fun deleteFavoriteMovie(idMovie: Int) {
        executor.execute { moviesDao.deleteFavoriteMovie(idMovie) }
    }


    override fun getDetailTvShow(
        tv_id: Int,
        api_key: String,
        language: String
    ): Single<DetailTvShow> {
        return apiService.getTvShowDetail(tv_id, api_key, language).map {
            itemDetailTvShowMapper.mapToDomain(it)
        }
    }

    override fun getFavoriteTvById(idTv: Int): Single<List<DetailTvShow>> =
        tvShowsDao.getFavoriteTvById(idTv).map {
            itemDetailTvShowEntityMapper.mapToListDomain(it)
        }

    override fun insertFavoriteTv(tvShow: DetailTvShow) {
        val entity = itemDetailTvShowEntityMapper.mapToModel(tvShow)
        executor.execute { tvShowsDao.insertFavoriteTv(entity) }
    }

    override fun deleteFavoriteTv(idTv: Int) {
        executor.execute { tvShowsDao.deleteFavoriteTv(idTv) }
    }

}