package com.example.moviesearch.di

import com.example.moviesearch.presentation.movies.MoviesSearchViewModel
import com.example.moviesearch.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get(), get())
    }

    viewModel {
        PosterViewModel()
    }

}