package com.jsouza.moviedetail.domain.usecase

import com.jsouza.moviedetail.domain.repository.FavoriteMoviesRepository

class SetIsFavoriteMovie(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) {
    operator fun invoke(isFavorite: Boolean) = favoriteMoviesRepository.setIsFavoriteMovie(isFavorite)
}
