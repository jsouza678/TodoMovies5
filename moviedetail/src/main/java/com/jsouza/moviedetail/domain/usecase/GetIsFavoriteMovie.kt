package com.jsouza.moviedetail.domain.usecase

import com.jsouza.moviedetail.domain.repository.FavoriteMoviesRepository

class GetIsFavoriteMovie(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) {
    operator fun invoke(): Boolean = favoriteMoviesRepository.getIsFavoriteMovie()
}
