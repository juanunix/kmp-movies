package org.juansanz.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kmp_movies.composeapp.generated.resources.Res
import kmp_movies.composeapp.generated.resources.api_key
import org.jetbrains.compose.resources.stringResource
import org.juansanz.kmpmovies.data.MoviesService
import org.juansanz.kmpmovies.data.movies
import org.juansanz.kmpmovies.ui.screens.home.DetailScreen
import org.juansanz.kmpmovies.ui.screens.home.HomeScreen
import org.juansanz.kmpmovies.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val client = remember {
                HttpClient {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }
            }
            val apiKey = stringResource(Res.string.api_key)
            val homeViewModel = viewModel {
                HomeViewModel(MoviesService(apiKey, client))
            }

            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate("detail/${movie.id}")
                },
                vm = homeViewModel
            )
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = { navController.popBackStack() })
        }
    }
}