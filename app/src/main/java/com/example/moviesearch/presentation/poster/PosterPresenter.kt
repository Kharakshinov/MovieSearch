package com.example.moviesearch.presentation.poster

class PosterPresenter(private val view: PosterView, private val posterUrl: String) {

    fun onCreate() {
        view.setPoster(posterUrl)
    }
}