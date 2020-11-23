package com.masuwes.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masuwes.moviecatalogue.data.repository.MovieRepositoryImpl
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.utils.room.Resource

class FavMovieViewModel(repositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val getMoviePage: LiveData<Resource<PagedList<DetailMovie>>> =
            repositoryImpl.getMoviePage()
}