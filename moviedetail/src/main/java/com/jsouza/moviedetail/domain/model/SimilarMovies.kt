package com.jsouza.moviedetail.domain.model

data class SimilarMovies(
    var id: Int,
    var title: String?,
    var genres: String?,
    var releaseDate: String?,
    var posterPath: String?
)
