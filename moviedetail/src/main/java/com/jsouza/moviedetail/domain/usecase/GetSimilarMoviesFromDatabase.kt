package com.jsouza.moviedetail.domain.usecase

import androidx.lifecycle.LiveData
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.domain.repository.SimilarMoviesRepository

class GetSimilarMoviesFromDatabase(
    private val similarMoviesRepository: SimilarMoviesRepository
) {
    operator fun invoke(): LiveData<List<SimilarMovies>?> = similarMoviesRepository.getSimilarMovies()
}
