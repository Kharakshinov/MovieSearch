package com.example.moviesearch.presentation.movies


interface MoviesView {
    fun render(state: MoviesState)

    fun showToast(additionalMessage: String)
}