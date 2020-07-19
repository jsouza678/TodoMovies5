package com.jsouza.moviedetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.moviedetail.data.remote.MovieDetailService
import com.jsouza.moviedetail.data.remote.response.SimilarMovies
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val fetchMovieDetailsFromApi: FetchMovieDetailsFromApi,
    private val getMovieDetailsFromDatabase: GetMovieDetailsFromDatabase,
    private val movieDetailService: MovieDetailService
) : ViewModel() {

    private val coroutineScope = Dispatchers.IO
    private val _similarList = MutableLiveData<List<SimilarMovies>>()
    val similarList: LiveData<List<SimilarMovies>> = _similarList
    fun returnMovieDetailOnLiveData(): LiveData<MovieDetail?> = getMovieDetailsFromDatabase.invoke(76341)

    init {
        viewModelScope.launch(context = coroutineScope) {
            fetchMovieDetailsFromApi(76341)
            try {
                _similarList.postValue(movieDetailService.fetchSimilarMoviesAsync(76341).await().results)
            } catch (e: Exception) {
            }
        }
    }
}
