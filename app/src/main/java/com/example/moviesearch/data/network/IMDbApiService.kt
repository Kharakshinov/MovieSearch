package com.example.moviesearch.data.network

import com.example.moviesearch.data.dto.MovieCastResponse
import com.example.moviesearch.data.dto.MoviesDetailsResponse
import com.example.moviesearch.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_man7mivu/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_man7mivu/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MoviesDetailsResponse>

    @GET("/en/API/FullCast/YOUR_API_KEY/{movie_id}")
    fun getFullCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>

}