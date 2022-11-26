package com.svoloshyn.giphytestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.svoloshyn.giphytestapp.ui.screens.gifScreen.GifScreen
import com.svoloshyn.giphytestapp.ui.screens.search.SearchScreen

@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Gif.route + "/{gifUrl}",
            arguments = listOf(
                navArgument("gifUrl") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            GifScreen(
                gifUrl = entry.arguments?.getString("gifUrl")
            )
        }
    }
}