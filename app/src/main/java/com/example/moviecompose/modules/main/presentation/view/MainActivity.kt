package com.example.moviecompose.modules.main.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviecompose.base.presentations.ui.theme.MovieComposeTheme
import com.example.moviecompose.core.navigation.MainFlow
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.details.presentation.navigation.DetailsNavHost
import com.example.moviecompose.modules.home.presentation.navigation.HomeFlow
import com.example.moviecompose.modules.home.presentation.navigation.HomeNavHost
import com.example.moviecompose.modules.splash.presentation.view.SplashScreen
import com.example.moviecompose.modules.splash.presentation.viewmodel.SplashViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainNavigationCoordinator: MainNavigationCoordinator

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {

                    val navController = rememberNavController()
                    mainNavigationCoordinator.init(navController)
                    NavHost(navController =navController ,startDestination = MainFlow.Root.route){
                        composable(route = MainFlow.Root.route) {
                            SplashScreen()
                        }

                        HomeNavHost(navController)

                        DetailsNavHost(navController)

                    }
                }
            }
        }
    }
}

