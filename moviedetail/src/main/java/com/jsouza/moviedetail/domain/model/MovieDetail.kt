package com.jsouza.moviedetail.domain.model

data class MovieDetail(
    var id: Int,
    var title: String?,
    var genres: String?,
    var releaseDate: String?,
    var posterImage: String?,
    var description: String?,
    var runtime: Int?,
    var popularity: Float?,
    var voteCount: Int?
)
