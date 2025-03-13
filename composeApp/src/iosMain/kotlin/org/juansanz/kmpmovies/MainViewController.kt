package org.juansanz.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import org.juansanz.kmpmovies.data.database.MoviesDatabase

fun MainViewController() = ComposeUIViewController {
    val database: MoviesDatabase = getRoomDatabase(getDatabaseBuilder())
    App(database.moviesDao())
}