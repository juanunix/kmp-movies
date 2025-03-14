package org.juansanz.kmpmovies.ui.screens.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.juansanz.kmpmovies.data.Movie
import org.juansanz.kmpmovies.data.MoviesRepository

class DetailViewModel(private val id: Int, private val repository: MoviesRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchMovieById(id).collect {
                it?.let { _state.value = UiState(loading = false, movie = it) }
            }
        }
    }

    fun onFavoriteClick() {
        _state.value.movie?.let {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }
        }
    }
}