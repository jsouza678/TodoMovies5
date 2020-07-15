package com.souza.todomovies5.data.todomovies.remote.response

import com.squareup.moshi.Json

data class SimilarMovies(
    val id: Int,
    val title: String?,
    @Json(name = "genre_ids") val genres: List<Int>?,
    @Json (name = "release_date") val releaseDate: String?,
    @Json (name = "poster_path") val posterPath: String?
)