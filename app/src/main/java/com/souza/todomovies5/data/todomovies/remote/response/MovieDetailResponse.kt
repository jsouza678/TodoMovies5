package com.souza.todomovies5.data.todomovies.remote.response

import com.souza.todomovies5.utils.Constants
import com.squareup.moshi.Json

data class MovieDetailResponse(
    val id: Int,
    val title: String?,
    val genres: List<MovieGenres>?,
    @Json (name = "release_date") val releaseDate: String?,
    @Json (name = "backdrop_path") val backdropPath: String?,
    @Json (name = "overview") val description: String?,
    val runtime: Int?,
    val popularity: Float?,
    @Json (name = "vote_count") val voteCount: Int?
) {
    fun getImageUrl() : String {
        val url = Constants.IMAGE_BASE_URL + backdropPath
        return url
    }

    fun getGenres(): String {
        var genresResult = ""

        if (genres != null) {
            for (element in genres) {
                genresResult = genres.joinToString(", "){it.name.toString()}
            }
        }

        return genresResult
    }
}