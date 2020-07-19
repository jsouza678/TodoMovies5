package com.jsouza.moviedetail.domain.repository

import androidx.lifecycle.LiveData
import com.jsouza.moviedetail.domain.model.MovieDetail

interface MovieDetailRepository {

    fun getMovieDetails(id: Int): LiveData<MovieDetail?>

    suspend fun refreshMovieDetails(id: Int)
}
