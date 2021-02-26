package com.masuwes.core.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.masuwes.core.Constants
import com.masuwes.core.data.local.database.AppDatabase
import com.masuwes.core.data.mapper.DetailMovieMapper
import com.masuwes.core.data.mapper.DetailTvShowMapper
import com.masuwes.core.data.mapper.MovieMapper
import com.masuwes.core.data.mapper.TvShowMapper
import com.masuwes.core.data.remote.ApiService
import com.masuwes.core.data.remote.BaseInterceptor
import com.masuwes.core.data.repository.DetailRepositoryImpl
import com.masuwes.core.data.repository.MovieRepositoryImpl
import com.masuwes.core.data.repository.TvShowRepositoryImpl
import com.masuwes.core.domain.repository.DetailRepository
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.domain.usecase.detail.DetailInteractor
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.core.domain.usecase.movie.MovieInteractor
import com.masuwes.core.domain.usecase.movie.MovieUseCase
import com.masuwes.core.domain.usecase.tvshow.TvShowInteractor
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    single { getExecutor() }
    single { AppExecutors() }

    single { get<AppDatabase>().moviesDao() }
    single { get<AppDatabase>().tvShowsDao() }
}

val networkModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val timeout = 60L
        val interceptor = BaseInterceptor()
        OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    single {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


val repositoryModule = module {

    single {
        MovieRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        ) as MovieRepository
    }

    single {
        TvShowRepositoryImpl(
            get(),
            get(),
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

    single { MovieRepositoryImpl(get(), get(), get(), get()) }
    single { TvShowRepositoryImpl(get(), get(), get(), get()) }

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

fun getExecutor(): Executor {
    return Executors.newFixedThreadPool(2)
}