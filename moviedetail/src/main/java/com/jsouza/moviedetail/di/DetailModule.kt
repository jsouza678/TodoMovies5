package com.jsouza.moviedetail.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jsouza.moviedetail.data.MovieDetailRepositoryImpl
import com.jsouza.moviedetail.data.local.MovieDetailDatabase
import com.jsouza.moviedetail.data.local.dao.MovieDetailsDao
import com.jsouza.moviedetail.data.remote.MovieDetailService
import com.jsouza.moviedetail.domain.repository.MovieDetailRepository
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import com.jsouza.moviedetail.presentation.MovieDetailsViewModel
import com.jsouza.moviedetail.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val movieDetailDatabase = "MOVIE_DETAILS_DATABASE"
private const val movieDetailDao = "MOVIE_DETAILS_DAO"

val detailModule = module {

    viewModel {
        MovieDetailsViewModel(
            get<FetchMovieDetailsFromApi>(),
            get<GetMovieDetailsFromDatabase>(),
            get<MovieDetailService>()
        )
    }

    single(named(movieDetailDatabase)) {
        Room.databaseBuilder(
            androidContext(),
            MovieDetailDatabase::class.java,
            "movie_details.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory {
        MovieDetailRepositoryImpl(
            get<MovieDetailService>(),
            get<MovieDetailsDao>(named(movieDetailDao))
        ) as MovieDetailRepository
    }

    single(named(movieDetailDao)) {
        get<MovieDetailDatabase>(named(movieDetailDatabase)).movieDetailsDao()
    }

    single {
        FetchMovieDetailsFromApi(
            get<MovieDetailRepository>()
        )
    }

    single {
        GetMovieDetailsFromDatabase(
            get<MovieDetailRepository>()
        )
    }

    single() {
        createRetrofit(
            get<OkHttpClient>()
        )
    }

    single() {
        createOkHttpClient()
    }

    single {
        getMovieDetailService(
            get<Retrofit>()
        )
    }
}

private fun getMovieDetailService(retrofit: Retrofit): MovieDetailService = retrofit
    .create(MovieDetailService::class.java)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttpClient)
    .baseUrl(Constants.BASE_URL)
    .build()

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}
