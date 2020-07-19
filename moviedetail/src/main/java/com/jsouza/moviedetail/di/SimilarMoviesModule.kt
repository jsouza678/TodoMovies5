package com.jsouza.moviedetail.di

import com.jsouza.moviedetail.data.moviedetails.remote.MovieDetailService
import com.jsouza.moviedetail.data.similarmovies.SimilarMoviesRepositoryImpl
import com.jsouza.moviedetail.data.similarmovies.local.dao.SimilarMoviesDao
import com.jsouza.moviedetail.domain.repository.SimilarMoviesRepository
import com.jsouza.moviedetail.domain.usecase.FetchSimilarMoviesFromApi
import com.jsouza.moviedetail.domain.usecase.GetSimilarMoviesFromDatabase
import com.jsouza.moviedetail.presentation.adapter.SimilarMoviesAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val similarMoviesModule = module {

    // Repository
    factory {
        SimilarMoviesRepositoryImpl(
            get<MovieDetailService>(named(moviesService)),
            get<SimilarMoviesDao>(named(similarMoviesDao))
        ) as SimilarMoviesRepository
    }

    // Usecases
    single {
        FetchSimilarMoviesFromApi(
            get<SimilarMoviesRepository>()
        )
    }

    single {
        GetSimilarMoviesFromDatabase(
            get<SimilarMoviesRepository>()
        )
    }

    // Adapter
    single {
        SimilarMoviesAdapter()
    }
}
