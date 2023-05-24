package com.example.moviesearch.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.moviesearch.util.Creator
import com.example.moviesearch.R
import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.models.Movie

class MoviesSearchPresenter(private val view: MoviesView,
                            private val context: Context) {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val movies = ArrayList<Movie>()

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    private val handler = Handler(Looper.getMainLooper())

    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {

            view.render(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                view.render(
                                    MoviesState.Error(
                                        errorMessage = context.getString(R.string.something_went_wrong)
                                    )
                                )
                            }

                            movies.isEmpty() -> {
                                view.render(
                                    MoviesState.Empty(
                                        message = context.getString(R.string.nothing_found)
                                    )
                                )
                            }

                            else -> {
                                view.render(
                                    MoviesState.Content(
                                        movies = movies
                                    )
                                )
                            }
                        }

                    }
                }
            })
        }
    }

    fun searchDebounce(changedText: String) {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }
}