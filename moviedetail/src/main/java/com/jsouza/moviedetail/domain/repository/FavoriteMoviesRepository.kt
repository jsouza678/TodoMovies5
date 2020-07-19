package com.jsouza.moviedetail.domain.repository

interface FavoriteMoviesRepository {

    fun getIsFavoriteMovie(): Boolean

    fun setIsFavoriteMovie(isFavoriteMovie: Boolean)
}
