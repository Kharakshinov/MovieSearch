package com.example.moviesearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesearch.R
import com.example.moviesearch.core.navigation.NavigatorHolder
import com.example.moviesearch.core.navigation.NavigatorImpl
import com.example.moviesearch.databinding.ActivityRootBinding
import com.example.moviesearch.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator = NavigatorImpl(
        fragmentContainerViewId = R.id.rootFragmentContainerView,
        fragmentManager = supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.openFragment(
                MoviesFragment()
            )
        }

    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }

}