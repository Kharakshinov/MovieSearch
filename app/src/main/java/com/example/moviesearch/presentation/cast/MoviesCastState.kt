package com.example.moviesearch.presentation.cast

import com.example.moviesearch.domain.models.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val movie: MovieCast,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}