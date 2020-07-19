package com.jsouza.moviedetail.domain.usecase

import androidx.lifecycle.LiveData
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.repository.MovieDetailRepository

class GetMovieDetailsFromDatabase(
    private val movieDetailsRepository: MovieDetailRepository
) {
    operator fun invoke(id: Int): LiveData<MovieDetail?> = movieDetailsRepository.getMovieDetails(id)
}
