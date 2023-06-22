package com.example.moviesearch.di

import com.example.moviesearch.presentation.movies.MoviesSearchViewModel
import com.example.moviesearch.presentation.poster.AboutViewModel
import com.example.moviesearch.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get(), get())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

}