package com.jsouza.moviedetail.data.moviedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jsouza.moviedetail.data.moviedetails.local.dao.MovieDetailsDao
import com.jsouza.moviedetail.data.moviedetails.mapper.MovieDetailMapper
import com.jsouza.moviedetail.data.moviedetails.remote.MovieDetailService
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRepositoryImpl(
    private val movieDetailService: MovieDetailService,
    private val movieDetailsDao: MovieDetailsDao
) : MovieDetailRepository {

    override fun getMovieDetails(id: Int): LiveData<MovieDetail?> {
        return Transformations.map(movieDetailsDao.getMovieDetails(id)) { movieDetailEntity ->
            movieDetailEntity?.let {
                MovieDetailMapper.toDomainModel(movieDetailEntity)
            }
        }
    }

    override suspend fun refreshMovieDetails(id: Int) {
        withContext(Dispatchers.IO) {
            try {
                val movieDetails = movieDetailService
                    .fetchMovieDetailsAsync(id)
                    .await()
                movieDetailsDao.insertAll(
                    MovieDetailMapper.toDatabaseModel(movieDetails)
                )
            } catch (e: Exception) {
                Log.i("Api Error", e.message.toString())
            }
        }
    }
}
