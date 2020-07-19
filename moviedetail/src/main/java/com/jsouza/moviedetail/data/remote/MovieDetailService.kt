package com.jsouza.moviedetail.data.remote

import com.jsouza.moviedetail.data.remote.response.MovieDetailResponse
import com.jsouza.moviedetail.data.remote.response.SimilarMoviesResponse
import com.jsouza.moviedetail.utils.Constants.Companion.API_KEY
import com.jsouza.moviedetail.utils.Constants.Companion.DEFAULT_LANGUAGE
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("{id}")
    fun fetchMovieDetailsAsync(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): Deferred<MovieDetailResponse>

    @GET("{id}/similar")
    fun fetchSimilarMoviesAsync(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): Deferred<SimilarMoviesResponse>
}
