package com.jsouza.moviedetail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsouza.moviedetail.data.local.dao.MovieDetailsDao
import com.jsouza.moviedetail.data.local.entities.MovieDetailEntity

@Database(
    entities = [MovieDetailEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDetailDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
}
