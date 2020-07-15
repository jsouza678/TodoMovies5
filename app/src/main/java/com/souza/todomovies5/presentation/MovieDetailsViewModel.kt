package com.souza.todomovies5.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.souza.todomovies5.data.todomovies.remote.MovieDetailApi
import com.souza.todomovies5.data.todomovies.remote.MovieDetailService
import com.souza.todomovies5.data.todomovies.remote.response.SimilarMovies
import com.souza.todomovies5.data.todomovies.remote.response.SimilarMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    private val service = MovieDetailApi.retrofitService
    private val coroutineScope = Dispatchers.IO
    private val _similarList = MutableLiveData<List<SimilarMovies>>()
    val similarList: LiveData<List<SimilarMovies>> = _similarList

    init {
        viewModelScope.launch(context = coroutineScope) {
            try{
                val movieList = service.fetchSimilarMoviesAsync(5).await()
                _similarList.postValue(movieList.results)
            }catch (e: Exception){
                Log.i("Error api", e.message.toString())
            }
        }
    }
}
