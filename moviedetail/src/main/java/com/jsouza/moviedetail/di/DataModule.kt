package com.jsouza.moviedetail.di

import androidx.room.Room
import com.jsouza.moviedetail.data.moviedetails.local.MovieDetailDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val movieDetailDatabase = "MOVIE_DETAILS_DATABASE"
const val similarMoviesDao = "SIMILAR_MOVIES_DAO"
const val movieDetailDao = "MOVIE_DETAILS_DAO"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val dataModule = module {

    // Database
    single(named(movieDetailDatabase)) {
        Room.databaseBuilder(
            androidContext(),
            MovieDetailDatabase::class.java,
            "movie_detailss.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // DAO
    single(named(movieDetailDao)) {
        get<MovieDetailDatabase>(named(movieDetailDatabase)).movieDetailsDao()
    }

    single(named(similarMoviesDao)) {
        get<MovieDetailDatabase>(named(movieDetailDatabase)).similarMoviesDao()
    }
}
