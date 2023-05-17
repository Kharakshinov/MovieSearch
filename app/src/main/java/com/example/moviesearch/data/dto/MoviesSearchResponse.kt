package com.example.moviesearch.data.dto

import com.example.moviesearch.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)