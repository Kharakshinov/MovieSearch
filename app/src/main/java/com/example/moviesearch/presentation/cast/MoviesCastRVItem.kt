package com.example.moviesearch.presentation.cast

import com.example.moviesearch.domain.models.MovieCastPerson
import com.example.moviesearch.util.ui.RVItem

sealed interface MoviesCastRVItem: RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}