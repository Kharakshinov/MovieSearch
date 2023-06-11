package com.example.moviesearch.ui.poster

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesearch.R
import com.example.moviesearch.presentation.poster.PosterState
import com.example.moviesearch.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PosterActivity : AppCompatActivity() {

    private lateinit var poster: ImageView
    private lateinit var posterUrl: String
    private val viewModel by viewModel<PosterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        poster = findViewById(R.id.poster)

        posterUrl = intent.extras?.getString("poster", "") ?: ""

        viewModel.showPoster()

        viewModel.state.observe(this){ state ->
            when(state){
                PosterState.Content -> setPoster()
            }
        }

    }

    private fun setPoster() {
        Glide.with(applicationContext)
            .load(posterUrl)
            .into(poster)
    }

}