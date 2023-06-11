package com.example.moviesearch.presentation.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PosterViewModel(): ViewModel() {

    private val _state = MutableLiveData<PosterState>()
    val state: LiveData<PosterState> = _state

    fun showPoster(){
        _state.postValue(PosterState.Content)
    }
}