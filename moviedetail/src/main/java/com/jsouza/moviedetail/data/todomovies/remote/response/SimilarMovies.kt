package com.jsouza.moviedetail.data.todomovies.remote.response

import com.jsouza.moviedetail.utils.Constants.Companion.IMAGE_BASE_URL
import com.squareup.moshi.Json

data class SimilarMovies(
    val id: Int,
    val title: String?,
    @Json(name = "genre_ids") val genres: List<Int>,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "poster_path") val posterPath: String?
) {
    fun getImageUrl(): String {
        val url = IMAGE_BASE_URL + posterPath
        return url
    }
}
