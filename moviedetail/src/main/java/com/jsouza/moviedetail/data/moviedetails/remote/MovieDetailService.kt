package com.jsouza.moviedetail.data.moviedetails.remote

import com.jsouza.moviedetail.data.moviedetails.remote.response.MovieDetailResponse
import com.jsouza.moviedetail.data.similarmovies.remote.response.SimilarMoviesResponse
import com.jsouza.moviedetail.utils.Constants.Companion.DEFAULT_LANGUAGE
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    companion object {
        private const val API_KEY = "b2334c6ca49dca959c447f8dcf3521a6"
    }

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
