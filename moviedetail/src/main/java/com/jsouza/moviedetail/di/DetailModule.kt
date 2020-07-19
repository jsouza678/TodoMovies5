package com.jsouza.moviedetail.di

import com.jsouza.moviedetail.data.moviedetails.MovieDetailRepositoryImpl
import com.jsouza.moviedetail.data.moviedetails.local.dao.MovieDetailsDao
import com.jsouza.moviedetail.data.moviedetails.remote.MovieDetailService
import com.jsouza.moviedetail.domain.repository.MovieDetailRepository
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.FetchSimilarMoviesFromApi
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import com.jsouza.moviedetail.domain.usecase.GetSimilarMoviesFromDatabase
import com.jsouza.moviedetail.presentation.MovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val detailModule = module {

    // ViewModel
    viewModel {
        MovieDetailsViewModel(
            get<FetchMovieDetailsFromApi>(),
            get<GetMovieDetailsFromDatabase>(),
            get<FetchSimilarMoviesFromApi>(),
            get<GetSimilarMoviesFromDatabase>()
        )
    }

    // Repository
    factory {
        MovieDetailRepositoryImpl(
            get<MovieDetailService>(named(moviesService)),
            get<MovieDetailsDao>(named(movieDetailDao))
        ) as MovieDetailRepository
    }

    // Usecases
    single {
        FetchMovieDetailsFromApi(
            get<MovieDetailRepository>()
        )
    }

    single {
        GetMovieDetailsFromDatabase(
            get<MovieDetailRepository>()
        )
    }
}
