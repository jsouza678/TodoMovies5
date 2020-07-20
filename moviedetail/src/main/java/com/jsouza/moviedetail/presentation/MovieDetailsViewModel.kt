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
    private val fetchMovieDetailsFromApi: FetchMovieDetailsFromApi,
    private val getMovieDetailsFromDatabase: GetMovieDetailsFromDatabase,
    private val fetchSimilarMoviesFromApi: FetchSimilarMoviesFromApi,
    private val getSimilarMoviesFromDatabase: GetSimilarMoviesFromDatabase,
    private val setIsFavoriteMovie: SetIsFavoriteMovie,
    private val getIsFavoriteMovie: GetIsFavoriteMovie
) : ViewModel() {

    private var wasConnected = true
    private var showConnectivityOnSnackbar = MutableLiveData<Unit>()
    private var showConnectivityOffSnackbar = MutableLiveData<Unit>()
    private val coroutineScope = Dispatchers.IO

    init {
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

    fun mustShowConnectivitySnackbar(
        hasNetworkConnectivity: Boolean
    ) {
        if (hasNetworkConnectivity.not()) {
            showConnectivityOffSnackbar.postValue(Unit)
            wasConnected = false
        } else if (wasConnected.not() && hasNetworkConnectivity) {
            showConnectivityOnSnackbar.postValue(Unit)
            wasConnected = true
        }
    }
}
