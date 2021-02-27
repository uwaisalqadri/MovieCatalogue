package com.masuwes.core.domain.usecase.tvshow

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.utils.Resource
import io.reactivex.Single

class TvShowInteractor(private val tvShowRepository: TvShowRepository) : TvShowUseCase {
    override fun getTvShows(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Single<List<TvShow>> =
        tvShowRepository.getTvShows(api_key, language, sort_by, page)

    override fun getTvShowsAsPaged(): DataSource.Factory<Int, DetailTvShow> =
        tvShowRepository.getTvShowsAsPaged()

    override fun getTvShowPage(): LiveData<Resource<PagedList<DetailTvShow>>> =
        tvShowRepository.getTvShowPage()

}