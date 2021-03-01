package com.masuwes.favorite.di

import com.masuwes.favorite.ui.movie.FavMovieViewModel
import com.masuwes.favorite.ui.tvshow.FavTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module {
    viewModel { FavMovieViewModel(get()) }
    viewModel { FavTvShowViewModel(get()) }
}