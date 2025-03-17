package org.juansanz.kmpmovies.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.juansanz.kmpmovies.ui.screens.details.DetailScreen
import org.juansanz.kmpmovies.ui.screens.home.HomeScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

private const val ANIM_DURATION = 350

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "home",
        enterTransition = {
            enterTransition()
        },
        exitTransition = {
            exitTransition()
        },
        popEnterTransition = {
            popEnterTransition()
        },
        popExitTransition = {
            popExitTransition()
        }
    ) {
        composable("home") {
            HomeScreen(
                onMovieClick = { navController.navigate("detail/${it.id}") },
            )
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                vm = koinViewModel(parameters = { parametersOf(movieId) }),
                onBack = { navController.popBackStack() })
        }
    }
}

fun popExitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { +it },
    animationSpec = tween(durationMillis = ANIM_DURATION)
) + fadeOut(
    animationSpec = tween(
        ANIM_DURATION, easing = LinearEasing
    )
)

fun popEnterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(durationMillis = ANIM_DURATION)
)

fun exitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(durationMillis = ANIM_DURATION)
) + fadeOut(
    animationSpec = tween(
        ANIM_DURATION, easing = LinearEasing
    )
)

fun enterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { +it },
    animationSpec = tween(durationMillis = ANIM_DURATION)
)