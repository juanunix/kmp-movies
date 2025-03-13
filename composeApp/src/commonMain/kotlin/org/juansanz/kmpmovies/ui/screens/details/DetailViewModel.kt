package org.juansanz.kmpmovies.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import org.juansanz.kmpmovies.data.Movie
import org.juansanz.kmpmovies.data.MoviesRepository

class DetailViewModel(private val id: Int, private val repository: MoviesRepository) :
    ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, movie = repository.fetchMovieById(id))
        }
    }
}