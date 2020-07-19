package com.jsouza.moviedetail.data.similarmovies.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SIMILAR_MOVIES_TABLE")
data class SimilarMoviesEntity(
    @PrimaryKey
    val _id: Int,
    val title: String?,
    val genres: String?,
    val releaseDate: String?,
    val posterPath: String?
)
