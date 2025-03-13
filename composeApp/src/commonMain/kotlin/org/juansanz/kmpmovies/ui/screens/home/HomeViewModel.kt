package org.juansanz.kmpmovies.ui.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.juansanz.kmpmovies.data.Movie
import org.juansanz.kmpmovies.data.MoviesRepository

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            moviesRepository.movies.collect {
                if (it.isNotEmpty()) {
                    state = UiState(loading = false, movies = it)
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
    )
}
