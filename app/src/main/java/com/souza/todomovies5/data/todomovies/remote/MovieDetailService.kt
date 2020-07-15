package com.souza.todomovies5.data.todomovies.remote

import com.souza.todomovies5.data.todomovies.remote.response.MovieDetailResponse
import com.souza.todomovies5.data.todomovies.remote.response.SimilarMoviesResponse
import com.souza.todomovies5.utils.Constants.Companion.API_KEY
import com.souza.todomovies5.utils.Constants.Companion.DEFAULT_LANGUAGE
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("{id}")
    fun fetchMovieDetailsAsync(@Path("id") id: Int,
                               @Query("api_key") api_key: String = API_KEY,
                               @Query("language") language: String = DEFAULT_LANGUAGE
    ): Deferred<MovieDetailResponse>

    @GET("{id}/similar")
    fun fetchSimilarMoviesAsync(@Path("id") id: Int,
                               @Query("api_key") api_key: String = API_KEY,
                               @Query("language") language: String = DEFAULT_LANGUAGE
    ): Deferred<SimilarMoviesResponse>
}

object MovieDetailApi{
    val retrofitService: MovieDetailService by lazy { retrofit.create(MovieDetailService::class.java) }
}