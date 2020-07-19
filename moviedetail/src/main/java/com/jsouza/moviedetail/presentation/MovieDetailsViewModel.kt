package com.jsouza.moviedetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.FetchSimilarMoviesFromApi
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import com.jsouza.moviedetail.domain.usecase.GetSimilarMoviesFromDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val fetchMovieDetailsFromApi: FetchMovieDetailsFromApi,
    private val getMovieDetailsFromDatabase: GetMovieDetailsFromDatabase,
    private val fetchSimilarMoviesFromApi: FetchSimilarMoviesFromApi,
    private val getSimilarMoviesFromDatabase: GetSimilarMoviesFromDatabase
) : ViewModel() {

    private val coroutineScope = Dispatchers.IO

    fun returnMovieDetailOnLiveData(): LiveData<MovieDetail?> = getMovieDetailsFromDatabase.invoke(76341)
    fun returnSimilarMoviesOnLiveData(): LiveData<List<SimilarMovies>?> = getSimilarMoviesFromDatabase.invoke()

    init {
        viewModelScope.launch(context = coroutineScope) {
            fetchMovieDetailsFromApi(76341)
            fetchSimilarMoviesFromApi(76341)
        }
    }
}
