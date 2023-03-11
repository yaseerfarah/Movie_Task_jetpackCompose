package com.example.moviecompose.modules.details.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


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