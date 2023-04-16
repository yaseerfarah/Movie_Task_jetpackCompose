package com.example.moviecompose.core.navigation

sealed class  MainNavigationEvent {
    object NavigateBackToHome:MainNavigationEvent()
    data class NavigateToDetailsScreen(val movieId:String?):MainNavigationEvent()
}