package com.example.moviecompose.core.navigation

import android.content.Context
import androidx.navigation.NavController
import com.example.moviecompose.base.presentations.navigation.NavigationCoordinator
import javax.inject.Inject

 class MainNavigationCoordinator @Inject constructor(val mainFlowNavigation: MainFlowNavigation): NavigationCoordinator<MainNavigationEvent>() {
     override fun init(param: Any) {
         mainFlowNavigation.setNavController(param as NavController)
     }

     override fun onStart(context: Context?, param: Any?) {

     }

     override fun onEvent(event: MainNavigationEvent) {
        when(event){
            is MainNavigationEvent.NavigateBackToHome->{mainFlowNavigation.onDetailsBackPress()}
            is MainNavigationEvent.NavigateToDetailsScreen->{mainFlowNavigation.openDetailsScreen(event.movieId)}
        }
     }


 }