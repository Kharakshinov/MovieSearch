package com.example.moviesearch.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentAboutBinding
import com.example.moviesearch.domain.models.MovieDetails
import com.example.moviesearch.presentation.poster.AboutState
import com.example.moviesearch.presentation.poster.AboutViewModel
import com.example.moviesearch.ui.cast.MoviesCastFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment: Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: String) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieId)
            }
        }
    }

    private val aboutViewModel: AboutViewModel by viewModel {
        parametersOf(requireArguments().getString(MOVIE_ID))
    }

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutViewModel.observeState().observe(viewLifecycleOwner) {
            when(it) {
                is AboutState.Content -> showDetails(it.movie)
                is AboutState.Error -> showErrorMessage(it.message)
            }
        }

        binding.showCastButton.setOnClickListener {
            parentFragment?.parentFragmentManager?.commit {
                replace(
                    R.id.rootFragmentContainerView,
                    MoviesCastFragment.newInstance(
                        movieId = requireArguments().getString(MOVIE_ID).orEmpty()
                    ),
                    MoviesCastFragment.TAG
                )
                addToBackStack(MoviesCastFragment.TAG)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }

    private fun showDetails(movieDetails: MovieDetails) {
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            filmName.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            countryValue.text = movieDetails.countries
            genreValue.text = movieDetails.genres
            directorValue.text = movieDetails.directors
            screenWriterValue.text = movieDetails.writers
            inRolesValue.text = movieDetails.stars
            plotValue.text = movieDetails.plot
        }

    }
}