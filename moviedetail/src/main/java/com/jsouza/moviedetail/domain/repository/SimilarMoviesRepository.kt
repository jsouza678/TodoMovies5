package com.jsouza.moviedetail.domain.repository

import androidx.lifecycle.LiveData
import com.jsouza.moviedetail.domain.model.SimilarMovies

interface SimilarMoviesRepository {

    fun getSimilarMovies(): LiveData<List<SimilarMovies>?>

    suspend fun refreshSimilarMovies(id: Int)
}
