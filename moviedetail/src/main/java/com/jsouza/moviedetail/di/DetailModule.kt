package com.jsouza.moviedetail.di

import com.jsouza.moviedetail.presentation.MovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {

    viewModel {
        MovieDetailsViewModel()
    }
}
