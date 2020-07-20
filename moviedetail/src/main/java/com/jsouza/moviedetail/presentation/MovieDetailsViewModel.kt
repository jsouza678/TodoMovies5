package com.jsouza.moviedetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.FetchSimilarMoviesFromApi
import com.jsouza.moviedetail.domain.usecase.GetIsFavoriteMovie
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import com.jsouza.moviedetail.domain.usecase.GetSimilarMoviesFromDatabase
import com.jsouza.moviedetail.domain.usecase.SetIsFavoriteMovie
import com.jsouza.moviedetail.utils.Constants.Companion.MOVIE_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    internal val fetchMovieDetailsFromApi: FetchMovieDetailsFromApi,
    internal val getMovieDetailsFromDatabase: GetMovieDetailsFromDatabase,
    internal val fetchSimilarMoviesFromApi: FetchSimilarMoviesFromApi,
    internal val getSimilarMoviesFromDatabase: GetSimilarMoviesFromDatabase,
    internal val setIsFavoriteMovie: SetIsFavoriteMovie,
    internal val getIsFavoriteMovie: GetIsFavoriteMovie
) : ViewModel() {

    internal var wasConnected = true
    internal var showConnectivityOnSnackbar = MutableLiveData<Unit>()
    internal var showConnectivityOffSnackbar = MutableLiveData<Unit>()
    internal val notConnectedToInternet = MutableLiveData<Unit>()
    internal val coroutineScope = Dispatchers.IO

    internal fun loadMovies() {
        if (wasConnected.not()) return
        viewModelScope.launch(context = coroutineScope) {
            fetchMovieDetailsFromApi(MOVIE_ID)
            fetchSimilarMoviesFromApi(MOVIE_ID)
        }
    }

    fun showConnectivityOnSnackbar(): LiveData<Unit> =
        showConnectivityOnSnackbar

    fun showConnectivityOffSnackbar(): LiveData<Unit> =
        showConnectivityOffSnackbar

    fun returnMovieDetailOnLiveData(): LiveData<MovieDetail?> =
        getMovieDetailsFromDatabase.invoke(MOVIE_ID)

    fun showSimilarMoviesOnLiveData(): LiveData<List<SimilarMovies>?> =
        getSimilarMoviesFromDatabase.invoke()

    fun getIsFavoriteMovieFromCache(): Boolean =
        getIsFavoriteMovie()

    fun setMovieAsFavoriteOnCache(isFavoriteMovie: Boolean) =
        setIsFavoriteMovie(isFavoriteMovie)

    fun updateConnectivityStatus(
        hasNetworkConnectivity: Boolean
    ) {
        if (hasNetworkConnectivity.not()) {
            showConnectivityOffSnackbar.postValue(Unit)
            notConnectedToInternet.postValue(Unit)
            wasConnected = false
        } else if (wasConnected.not() && hasNetworkConnectivity) {
            showConnectivityOnSnackbar.postValue(Unit)
            wasConnected = true
        }
    }

    fun refreshMovies() {
        viewModelScope.launch(context = coroutineScope) {
            fetchMovieDetailsFromApi(MOVIE_ID)
            fetchSimilarMoviesFromApi(MOVIE_ID)
        }
    }
}
