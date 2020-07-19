package com.jsouza.moviedetail.di

import com.jsouza.moviedetail.data.favorite.FavoriteMoviesRepositoryImpl
import com.jsouza.moviedetail.data.favorite.local.FavoriteCache
import com.jsouza.moviedetail.domain.repository.FavoriteMoviesRepository
import com.jsouza.moviedetail.domain.usecase.GetIsFavoriteMovie
import com.jsouza.moviedetail.domain.usecase.SetIsFavoriteMovie
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val favoriteMoviesModule = module {

    // Repository
    factory {
        FavoriteMoviesRepositoryImpl(
            androidContext(),
            get<FavoriteCache>()
        ) as FavoriteMoviesRepository
    }

    single {
        FavoriteCache()
    }

    // Usecases
    single {
        GetIsFavoriteMovie(
            get<FavoriteMoviesRepository>()
        )
    }

    single {
        SetIsFavoriteMovie(
            get<FavoriteMoviesRepository>()
        )
    }
}
