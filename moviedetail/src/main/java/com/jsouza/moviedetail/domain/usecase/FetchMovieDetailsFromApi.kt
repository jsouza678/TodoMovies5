package com.jsouza.moviedetail.domain.usecase

import com.jsouza.moviedetail.domain.repository.MovieDetailRepository

class FetchMovieDetailsFromApi(
    private val movieDetailsRepository: MovieDetailRepository
) {
    suspend operator fun invoke(id: Int) = movieDetailsRepository.refreshMovieDetails(id)
}
