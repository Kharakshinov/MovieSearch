package com.example.moviesearch.domain.api

import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieDetails

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)
    fun getMovieDetails(movieId: String, consumer: MoviesDetailsConsumer)
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface MoviesDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }
}