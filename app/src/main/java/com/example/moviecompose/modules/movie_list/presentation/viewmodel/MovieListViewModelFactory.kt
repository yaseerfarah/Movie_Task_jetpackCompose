package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecompose.core.navigation.MainNavigationCoordinator

class MovieListViewModelFactory(val mainNavigationCoordinator: MainNavigationCoordinator):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(mainNavigationCoordinator = mainNavigationCoordinator) as T
    }
}