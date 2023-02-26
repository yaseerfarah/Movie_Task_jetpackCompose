package com.example.moviecompose.modules.splash.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.HandleEffect
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUIEffects
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUIEvents
import com.example.moviecompose.modules.splash.presentation.viewmodel.SplashViewModel


@ExperimentalMaterial3Api
@Composable
fun SplashScreen(
    viewModel:SplashViewModel,
    callNextScreen:()->Unit
) {
    
    val uiModel=viewModel.uiModel.collectAsStateWithLifecycle()


    HandleEffect(viewModel){
        when(it){
            is SplashUIEffects.PassToNextScreen->{callNextScreen()}
        }
    }

    LaunchedEffect(uiModel){
        viewModel.sendEvent(SplashUIEvents.StartTimer)
    }

    Scaffold(
        content = {
                Column (
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color.White),
                  verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Image(painter = painterResource(id = R.drawable.app_logo)
                            , contentDescription ="Splash",
                        )
                    }
        }
    )

}