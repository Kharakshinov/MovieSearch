package com.example.moviesearch.presentation.poster

sealed interface PosterState{
    object Content: PosterState
}