package com.example.moviecompose.modules.home.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument



sealed class HomeFlow(val name:String){

    object Root:HomeFlow("home-root"){
        val route = name
    }

    object MainScreen:HomeFlow("home-main"){
        val route = name
    }

}