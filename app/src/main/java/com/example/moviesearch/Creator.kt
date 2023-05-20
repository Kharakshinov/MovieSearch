package com.example.moviesearch

import android.app.Activity
import com.example.moviesearch.data.MoviesRepositoryImpl
import com.example.moviesearch.data.network.RetrofitNetworkClient
import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.api.MoviesRepository
import com.example.moviesearch.domain.impl.MoviesInteractorImpl
import com.example.moviesearch.presentation.MoviesSearchController
import com.example.moviesearch.presentation.PosterController
import com.example.moviesearch.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}