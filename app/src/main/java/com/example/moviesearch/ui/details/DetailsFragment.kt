package com.example.moviesearch.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentDetailsBinding
import com.example.moviesearch.ui.poster.DetailsViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment: Fragment() {

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"
        private const val ARGS_POSTER_URL = "poster_url"

        const val TAG = "DetailsFragment"

        fun newInstance(movieId: String, posterUrl: String): Fragment {
            return DetailsFragment().apply {
                arguments = bundleOf(
                    ARGS_MOVIE_ID to movieId,
                    ARGS_POSTER_URL to posterUrl
                )
            }
        }

    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val poster = requireArguments().getString("poster_url") ?: ""
        val movieId = requireArguments().getString("movie_id") ?: ""

        binding.viewPager.adapter = DetailsViewPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle,
            posterUrl = poster,
            movieId = movieId,)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }

        tabMediator.attach()
    }

        override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}