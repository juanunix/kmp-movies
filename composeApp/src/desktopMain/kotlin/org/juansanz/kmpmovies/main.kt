package org.juansanz.kmpmovies

import App
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-movies",
    ) {
//        App()
    }
}