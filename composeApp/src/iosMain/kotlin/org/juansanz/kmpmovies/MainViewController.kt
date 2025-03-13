package org.juansanz.kmpmovies

import App
import androidx.compose.ui.window.ComposeUIViewController
import org.juansanz.kmpmovies.data.database.MoviesDatabase
import org.juansanz.kmpmovies.data.database.getRoomDatabase
import org.juansanz.kmpmovies.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val database: MoviesDatabase = getRoomDatabase(getDatabaseBuilder())
    App(database.moviesDao())
}