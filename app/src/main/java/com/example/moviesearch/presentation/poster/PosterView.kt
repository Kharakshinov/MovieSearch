package com.example.moviesearch.presentation.poster

import moxy.MvpView

interface PosterView: MvpView {

    fun setPoster(posterUrl: String)

}