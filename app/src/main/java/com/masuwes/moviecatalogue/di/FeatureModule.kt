package com.masuwes.moviecatalogue.di

import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieViewModel
import com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowViewModel
import com.masuwes.moviecatalogue.presentation.main.MainViewModel
import com.masuwes.moviecatalogue.presentation.movie.MovieViewModel
import com.masuwes.moviecatalogue.presentation.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { MainViewModel() }
}



















