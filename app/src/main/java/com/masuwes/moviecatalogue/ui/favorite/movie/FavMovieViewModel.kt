package com.masuwes.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masuwes.core.data.repository.MovieRepositoryImpl
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Resource

class FavMovieViewModel(repositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val getMoviePage: LiveData<Resource<PagedList<DetailMovie>>> =
            repositoryImpl.getMoviePage()
}