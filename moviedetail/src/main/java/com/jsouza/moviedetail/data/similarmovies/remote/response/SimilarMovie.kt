package com.jsouza.moviedetail.data.similarmovies.remote.response

import com.jsouza.moviedetail.utils.Constants.Companion.IMAGE_BASE_URL
import com.squareup.moshi.Json

data class SimilarMovie(
    val id: Int,
    val title: String?,
    @Json(name = "genre_ids") val genres: List<Int> = emptyList(),
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "poster_path") val posterPath: String?
) {
    fun getImageUrl(): String {
        return IMAGE_BASE_URL + posterPath
    }

    fun getGenres(): String {
        val genresList = mutableListOf<String>()
        for (i in genres.indices) {
            genres[i].let { genre ->
                when (genre) {
                    28 -> genresList.add("Ação")
                    12 -> genresList.add("Aventura")
                    16 -> genresList.add("Animação")
                    35 -> genresList.add("Comédia")
                    80 -> genresList.add("Crime")
                    99 -> genresList.add("Documentário")
                    18 -> genresList.add("Drama")
                    10751 -> genresList.add("Família")
                    14 -> genresList.add("Fantasia")
                    36 -> genresList.add("História")
                    27 -> genresList.add("Terror")
                    10402 -> genresList.add("Música")
                    9648 -> genresList.add("Mistério")
                    10749 -> genresList.add("Romance")
                    878 -> genresList.add("Ficção científica")
                    10770 -> genresList.add("Cinema TV")
                    53 -> genresList.add("Thriller")
                    10752 -> genresList.add("Guerra")
                    37 -> genresList.add("Faroeste")
                    else -> {}
                }
            }
        }

        return genresList.joinToString(", ")
    }
}
