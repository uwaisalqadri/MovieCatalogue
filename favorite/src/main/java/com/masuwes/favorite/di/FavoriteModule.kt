package com.masuwes.favorite.di

import com.masuwes.favorite.presentation.movie.FavoriteMovieViewModel
import com.masuwes.favorite.presentation.tvshow.FavoriteTvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}