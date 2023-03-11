package com.example.moviecompose.core.navigation

import androidx.navigation.NavController
import com.example.moviecompose.base.presentations.navigation.NavigationCoordinator
import javax.inject.Inject

class MainFlowNavigation @Inject constructor(){

    private lateinit var navController: NavController


    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun openDetailsScreen(movieId:String) {

    }

    fun onDetailsBackPress() {
        navController.popBackStack()
    }

}