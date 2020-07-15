package com.souza.todomovies5.data.todomovies.remote.response

import com.squareup.moshi.Json

data class MovieDetailResponse(
    val id: Int,
    val title: String?,
    val genres: List<MovieGenres>?,
    @Json (name = "release_date") val releaseDate: String?,
    @Json (name = "backdrop_path") val backdropPath: String?,
    @Json (name = "overview") val description: String?,
    val runtime: Int?,
    val popularity: Int?,
    @Json (name = "vote_count") val voteCount: Int?
)