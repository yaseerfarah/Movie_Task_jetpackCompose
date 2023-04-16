package com.example.moviecompose.modules.details.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviecompose.modules.details.presentation.view.DetailsScreen
import com.example.moviecompose.modules.home.presentation.view.HomeScreen
import com.example.moviecompose.modules.details.presentation.viewmodel.DetailsViewModel
import com.example.moviecompose.modules.home.presentation.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.DetailsNavHost(navController: NavHostController) {
    navigation(
        startDestination = DetailsFlow.DetailsScreen.route,
        route = DetailsFlow.Root.route
    ) {
        composable(route = DetailsFlow.DetailsScreen.route, arguments = DetailsFlow.DetailsScreen.arguments) {
            val viewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(
                viewModel = viewModel,
            )
        }
    }
}