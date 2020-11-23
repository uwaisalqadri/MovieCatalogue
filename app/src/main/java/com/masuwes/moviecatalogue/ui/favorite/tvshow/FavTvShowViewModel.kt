package com.masuwes.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masuwes.moviecatalogue.data.repository.TvShowRepositoryImpl
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.utils.room.Resource

class FavTvShowViewModel(repositoryImpl: TvShowRepositoryImpl) : ViewModel() {

    val getTvShowPage: LiveData<Resource<PagedList<DetailTvShow>>> =
            repositoryImpl.getTvShowPage()
}