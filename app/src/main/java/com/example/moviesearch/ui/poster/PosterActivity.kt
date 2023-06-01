package com.example.moviesearch.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.moviesearch.util.Creator
import com.example.moviesearch.R
import com.example.moviesearch.presentation.poster.PosterPresenter
import com.example.moviesearch.presentation.poster.PosterView

class PosterActivity : Activity(), PosterView {

    private lateinit var poster: ImageView

    private lateinit var posterPresenter : PosterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        poster = findViewById(R.id.poster)

        val posterUrl = intent.extras?.getString("poster", "") ?: ""

        posterPresenter = Creator.providePosterPresenter(this, posterUrl)

        posterPresenter.onCreate()
    }

    override fun setPoster(posterUrl: String) {
        Glide.with(applicationContext)
            .load(posterUrl)
            .into(poster)
    }


}