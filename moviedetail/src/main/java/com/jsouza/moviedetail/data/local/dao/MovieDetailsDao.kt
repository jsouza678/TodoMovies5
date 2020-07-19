package com.jsouza.moviedetail.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsouza.moviedetail.data.local.entities.MovieDetailEntity

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: MovieDetailEntity?)

    @Query("SELECT * FROM MOVIE_DETAILS_TABLE WHERE MOVIE_DETAILS_TABLE._id = :id")
    fun getMovieDetails(id: Int): LiveData<MovieDetailEntity?>
}
