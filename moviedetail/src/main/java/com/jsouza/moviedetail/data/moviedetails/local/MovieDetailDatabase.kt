package com.jsouza.moviedetail.data.moviedetails.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsouza.moviedetail.data.moviedetails.local.dao.MovieDetailsDao
import com.jsouza.moviedetail.data.moviedetails.local.entities.MovieDetailEntity
import com.jsouza.moviedetail.data.similarmovies.local.dao.SimilarMoviesDao
import com.jsouza.moviedetail.data.similarmovies.local.entities.SimilarMoviesEntity

@Database(
    entities = [
        MovieDetailEntity::class,
        SimilarMoviesEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDetailDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun similarMoviesDao(): SimilarMoviesDao
}
