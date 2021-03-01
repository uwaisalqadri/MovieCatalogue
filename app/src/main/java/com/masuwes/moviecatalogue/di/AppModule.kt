package com.masuwes.moviecatalogue.di

import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieVM
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowVM
import com.masuwes.moviecatalogue.ui.movie.MovieViewModel
import com.masuwes.moviecatalogue.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

    viewModel {
        DetailMovieVM(
        get())
    }
    viewModel {
        DetailTvShowVM(
        get())
    }
}



















