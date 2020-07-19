package com.jsouza.moviedetail.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.moviedetail.data.todomovies.remote.MovieDetailApi
import com.jsouza.moviedetail.data.todomovies.remote.response.MovieDetailResponse
import com.jsouza.moviedetail.data.todomovies.remote.response.SimilarMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    private val service = MovieDetailApi.retrofitService
    private val coroutineScope = Dispatchers.IO
    private val _similarList = MutableLiveData<List<SimilarMovies>>()
    val similarList: LiveData<List<SimilarMovies>> = _similarList
    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse> = _movieDetail

    init {
        viewModelScope.launch(context = coroutineScope) {
            try {
                val movieDetail = service.fetchMovieDetailsAsync(76341).await()
                val movieList = service.fetchSimilarMoviesAsync(76341).await()
                _similarList.postValue(movieList.results)
                _movieDetail.postValue(movieDetail)
            } catch (e: Exception) {
                Log.i("Error api", e.message.toString())
            }
        }
    }
}
