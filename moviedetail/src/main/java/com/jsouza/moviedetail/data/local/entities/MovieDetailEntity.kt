package com.jsouza.moviedetail.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE_DETAILS_TABLE")
data class MovieDetailEntity(
    @PrimaryKey
    val _id: Int,
    val title: String?,
    val genres: String?,
    val releaseDate: String?,
    val backdropPath: String?,
    val description: String?,
    val runtime: Int?,
    val popularity: Float?,
    val voteCount: Int?
)
