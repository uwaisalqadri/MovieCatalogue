package com.masuwes.favorite.di

import com.masuwes.favorite.presentation.movie.FavMovieViewModel
import com.masuwes.favorite.presentation.tvshow.FavTvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module {
    viewModel { FavMovieViewModel(get()) }
    viewModel { FavTvShowViewModel(get()) }
}