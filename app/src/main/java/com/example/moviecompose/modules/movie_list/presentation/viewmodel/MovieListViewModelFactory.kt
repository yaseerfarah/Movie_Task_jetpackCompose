package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
@Immutable
class MovieListViewModelFactory(val movieListInjectionProvider: MovieListInjectionProvider):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(mainNavigationCoordinator = movieListInjectionProvider.mainNavigationCoordinator,movieListInjectionProvider.getMoviesByCategoryUseCase) as T
    }
}