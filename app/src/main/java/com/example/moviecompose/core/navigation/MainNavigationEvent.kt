package com.example.moviecompose.core.navigation

import com.example.moviecompose.modules.core.domain.entity.MovieEntity

sealed class  MainNavigationEvent {
    object NavigateBackToHome:MainNavigationEvent()
    data class NavigateToDetailsScreen(val movieEntity: MovieEntity):MainNavigationEvent()
}