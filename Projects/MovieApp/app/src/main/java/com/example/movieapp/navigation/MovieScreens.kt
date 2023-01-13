package com.example.movieapp.navigation

enum class MovieScreens {
    HomeScreen,DetailsScreen;
    companion object {
        //相當於static
        fun fromRoute(route: String ?):MovieScreens  =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognize")
            }
    }
}