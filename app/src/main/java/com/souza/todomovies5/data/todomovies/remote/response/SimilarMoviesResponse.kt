package com.souza.todomovies5.data.todomovies.remote.response

import com.squareup.moshi.Json

data class SimilarMoviesResponse(
    val results: List<SimilarMovies>
)