package com.example.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_man7mivu/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}