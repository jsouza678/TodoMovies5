package com.jsouza.moviedetail.data.similarmovies.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsouza.moviedetail.data.similarmovies.local.entities.SimilarMoviesEntity

@Dao
interface SimilarMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg similarMovies: SimilarMoviesEntity)

    @Query("SELECT * FROM SIMILAR_MOVIES_TABLE")
    fun getSimilarMovies(): LiveData<List<SimilarMoviesEntity>?>
}
