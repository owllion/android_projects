package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.navigation.screens.home.HomeScreen
import com.example.movieapp.navigation.screens.home.detail.DetailScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name ) {
        //NavHost內層就是 NavGraphBuilder
        //真的就是向老師那張圖顯示的(可以看一下NavHost的scope 就是graph!)
        composable(
            route = MovieScreens.HomeScreen.name,
        ) {
            HomeScreen(

                onCardClicked = {
                        movieName ->
                    navController.navigate(MovieScreens.DetailsScreen.name+"/${movieName}")
                })
        }
        composable(
            //傳參數，要傳在route裡面，還要另外寫arguments，他用來說明詳細參數type那些
            route = MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(
                navArgument(name = "movie") { type = NavType.StringType})
        ) {
            backStackEntry ->
            DetailScreen(
                navigateUp = { navController.navigateUp()},
                movieName = backStackEntry.arguments?.getString("movie")
            )

        }
    }


}