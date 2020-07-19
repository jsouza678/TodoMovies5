package com.jsouza.moviedetail.data.similarmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jsouza.moviedetail.data.moviedetails.remote.MovieDetailService
import com.jsouza.moviedetail.data.similarmovies.local.dao.SimilarMoviesDao
import com.jsouza.moviedetail.data.similarmovies.mapper.SimilarMoviesMapper
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.domain.repository.SimilarMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimilarMoviesRepositoryImpl(
    private val similarMoviesService: MovieDetailService,
    private val similarMoviesDao: SimilarMoviesDao
) : SimilarMoviesRepository {

    override fun getSimilarMovies(): LiveData<List<SimilarMovies>?> {
        return Transformations.map(similarMoviesDao.getSimilarMovies()) { movieDetailEntity ->
            movieDetailEntity?.let {
                SimilarMoviesMapper.toDomainModel(movieDetailEntity)
            }
        }
    }

    override suspend fun refreshSimilarMovies(
        id: Int
    ) {
        withContext(Dispatchers.IO) {
            try {
                val similarMovies = similarMoviesService
                    .fetchSimilarMoviesAsync(id)
                    .await()

                SimilarMoviesMapper
                    .toDatabaseModel(similarMovies)?.let {
                        similarMoviesDao.insertAll(*it)
                    }
            } catch (e: Exception) {
                Log.i("Api Error", e.message.toString())
            }
        }
    }
}
