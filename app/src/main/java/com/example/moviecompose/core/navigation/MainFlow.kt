package com.example.moviecompose.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class MainFlow (val name:String){

    object Root:MainFlow("main-root"){
        val route = name
    }

    object SplashScreen:MainFlow("main-splash"){
        val route = name
    }


}

sealed class HomeFlow(val name:String){

    object Root:HomeFlow("home-root"){
        val route = name
    }

    object MainScreen:HomeFlow("home-main"){
        val route = name
    }

}

sealed class DetailsFlow(val name:String){

    object Root:DetailsFlow("details-root"){
        val route = "$name?$ARG_MOVIE_ID={$ARG_MOVIE_ID}"

        fun route(movieId:String):String{
            return "$name?$ARG_MOVIE_ID=${movieId}"
        }

    }

    object DetailsScreen:DetailsFlow("details-screen"){

        val arguments = listOf(
            navArgument(ARG_MOVIE_ID) {
                type = NavType.StringType
            }
        )

        val route = "$name?$ARG_MOVIE_ID={$ARG_MOVIE_ID}"
    }

}

const val ARG_MOVIE_ID = "movieId"