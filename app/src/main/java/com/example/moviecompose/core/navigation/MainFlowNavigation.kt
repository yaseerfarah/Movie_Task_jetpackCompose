package com.example.moviecompose.core.navigation

import android.util.Log
import androidx.navigation.NavController
import com.example.moviecompose.base.presentations.navigation.NavigationCoordinator
import com.example.moviecompose.modules.details.presentation.navigation.DetailsFlow
import com.example.moviecompose.modules.details.presentation.view.DetailsScreen
import com.example.moviecompose.modules.home.presentation.navigation.HomeFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainFlowNavigation @Inject constructor(){

    private lateinit var navController: NavController


    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun openDetailsScreen(movieId:String?) {
        navController.navigate(DetailsFlow.Root.route(movieId))
    }

    fun onDetailsBackPress() {
        navController.popBackStack()
    }

    fun goToHomeScreen() {
        Log.e("SplashScreen","SplashScreenNavigate")
        navController.navigate(HomeFlow.Root.route) {
            popUpTo(MainFlow.Root.route) {
                inclusive = true
            }
        }
    }

}