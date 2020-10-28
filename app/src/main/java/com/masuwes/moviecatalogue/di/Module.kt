package com.masuwes.moviecatalogue.di

import com.google.gson.GsonBuilder
import com.masuwes.moviecatalogue.data.mapper.DetailMovieMapper
import com.masuwes.moviecatalogue.data.mapper.DetailTvShowMapper
import com.masuwes.moviecatalogue.data.mapper.MovieMapper
import com.masuwes.moviecatalogue.data.mapper.TvShowMapper
import com.masuwes.moviecatalogue.data.repository.DetailRepositoryImpl
import com.masuwes.moviecatalogue.data.repository.MovieRepositoryImpl
import com.masuwes.moviecatalogue.data.repository.TvShowRepositoryImpl
import com.masuwes.moviecatalogue.data.service.ApiService
import com.masuwes.moviecatalogue.data.service.LoggingInterceptor
import com.masuwes.moviecatalogue.domain.repository.DetailRepository
import com.masuwes.moviecatalogue.domain.repository.MovieRepository
import com.masuwes.moviecatalogue.domain.repository.TvShowRepository
import com.masuwes.moviecatalogue.domain.usecase.movie.*
import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowInteractor
import com.masuwes.moviecatalogue.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.moviecatalogue.ui.detail.DetailViewModel
import com.masuwes.moviecatalogue.ui.movie.MovieViewModel
import com.masuwes.moviecatalogue.ui.tvshow.TvShowViewModel
import com.masuwes.moviecatalogue.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single { LoggingInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<ApiService>(get(), Constants.BASE_URL) }
}

val repositoryModule = module {

    single {
        MovieRepositoryImpl(
            get(),
            get()
        ) as MovieRepository
    }

    single {
        TvShowRepositoryImpl(
            get(),
            get()
        ) as TvShowRepository
    }

    single {
        DetailRepositoryImpl(
            get(),
            get(),
            get()
        ) as DetailRepository
    }

}

val mapperModule = module {
    single { MovieMapper() }
    single { TvShowMapper() }
    single { DetailMovieMapper() }
    single { DetailTvShowMapper() }
}

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
    factory<DetailUseCase> { DetailInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

fun createOkHttpClient(interceptor: LoggingInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 60L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}



















