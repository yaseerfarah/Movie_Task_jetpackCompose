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