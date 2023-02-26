package com.example.moviecompose.modules.home.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecompose.core.navigation.HomeFlow
import com.example.moviecompose.core.navigation.MainFlow
import com.example.moviecompose.modules.home.presentation.view.HomeScreen
import com.example.moviecompose.modules.home.presentation.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.HomeNavHost(navController: NavHostController) {
    navigation(
        startDestination = HomeFlow.MainScreen.route,
        route = HomeFlow.Root.route
    ) {
        composable(route = HomeFlow.MainScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                callNextScreen = {

                }
            )
        }
    }
}