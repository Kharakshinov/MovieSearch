package com.example.moviesearch.data

import com.example.moviesearch.data.converters.MovieCastConverter
import com.example.moviesearch.data.dto.*
import com.example.moviesearch.domain.api.MoviesRepository
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MovieCast
import com.example.moviesearch.domain.models.MovieDetails
import com.example.moviesearch.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()

                with(response as MoviesSearchResponse) {
                    Resource.Success(results.map {
                        Movie(it.id, it.resultType, it.image, it.title, it.description, inFavorite = stored.contains(it.id))})
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MoviesDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MoviesDetailsResponse) {
                    Resource.Success(
                        MovieDetails(id, title, imDbRating, year,
                        countries, genres, directors, writers, stars, plot)
                    )
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                )
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}