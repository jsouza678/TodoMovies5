package com.jsouza.moviedetail.domain.usecase

import com.jsouza.moviedetail.domain.repository.SimilarMoviesRepository

class FetchSimilarMoviesFromApi(
    private val similarMoviesRepository: SimilarMoviesRepository
) {
    suspend operator fun invoke(id: Int) = similarMoviesRepository.refreshSimilarMovies(id)
}
