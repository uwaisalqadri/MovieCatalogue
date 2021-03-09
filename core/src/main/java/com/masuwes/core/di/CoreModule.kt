package com.masuwes.core.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.masuwes.core.data.mapper.entity.DetailMovieEntityMapper
import com.masuwes.core.data.mapper.entity.DetailTvShowEntityMapper
import com.masuwes.core.data.mapper.entity.SearchEntityMapper
import com.masuwes.core.data.mapper.response.*
import com.masuwes.core.data.repository.DetailRepositoryImpl
import com.masuwes.core.data.repository.MovieRepositoryImpl
import com.masuwes.core.data.repository.SearchRepositoryImpl
import com.masuwes.core.data.repository.TvShowRepositoryImpl
import com.masuwes.core.data.source.local.AppDatabase
import com.masuwes.core.data.source.remote.ApiService
import com.masuwes.core.data.source.remote.BaseInterceptor
import com.masuwes.core.domain.repository.DetailRepository
import com.masuwes.core.domain.repository.MovieRepository
import com.masuwes.core.domain.repository.SearchRepository
import com.masuwes.core.domain.repository.TvShowRepository
import com.masuwes.core.domain.usecase.detail.DetailInteractor
import com.masuwes.core.domain.usecase.detail.DetailUseCase
import com.masuwes.core.domain.usecase.movie.MovieInteractor
import com.masuwes.core.domain.usecase.movie.MovieUseCase
import com.masuwes.core.domain.usecase.search.SearchInteractor
import com.masuwes.core.domain.usecase.search.SearchUseCase
import com.masuwes.core.domain.usecase.tvshow.TvShowInteractor
import com.masuwes.core.domain.usecase.tvshow.TvShowUseCase
import com.masuwes.core.utils.AppExecutors
import com.masuwes.core.utils.Constants
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("uwais".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single { getExecutor() }
    single { AppExecutors() }

    single { get<AppDatabase>().moviesDao() }
    single { get<AppDatabase>().tvShowsDao() }
    single { get<AppDatabase>().searchHistoryDao() }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val timeout = 60L
        val interceptor = BaseInterceptor()

        OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .certificatePinner(certificatePinner)
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

    single<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<TvShowRepository> {
        TvShowRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<DetailRepository> {
        DetailRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    single<SearchRepository> {
        SearchRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}

val mapperModule = module {
    single { MovieMapper() }
    single { TvShowMapper() }
    single { SearchMapper() }
    single { DetailMovieMapper() }
    single { DetailTvShowMapper() }

    single { DetailMovieEntityMapper() }
    single { DetailTvShowEntityMapper() }
    single { SearchEntityMapper() }
}

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
    factory<SearchUseCase> { SearchInteractor(get()) }
    factory<DetailUseCase> { DetailInteractor(get()) }
}

fun getExecutor(): Executor {
    return Executors.newFixedThreadPool(2)
}