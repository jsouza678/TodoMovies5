package com.jsouza.moviedetail.domain.model

data class MovieDetail(
    val _id: Int,
    val title: String?,
    val genres: String?,
    val releaseDate: String?,
    val posterImage: String?,
    val description: String?,
    val runtime: Int?,
    val popularity: Float?,
    val voteCount: Int?
)
