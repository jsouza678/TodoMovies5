package com.jsouza.moviedetail.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jsouza.moviedetail.data.moviedetails.remote.MovieDetailService
import com.jsouza.moviedetail.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val moviesService = "RETROFIT_SERVICE"
private const val retrofit = "RETROFIT"
private const val okHttp = "OKHTTP"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val serviceModule = module {

    single(named(retrofit)) {
        createRetrofit(
            get<OkHttpClient>(named(okHttp))
        )
    }

    factory(named(okHttp)) {
        createOkHttpClient()
    }

    single(named(moviesService)) {
        getMovieDetailService(
            get<Retrofit>(named(retrofit))
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

private fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}
