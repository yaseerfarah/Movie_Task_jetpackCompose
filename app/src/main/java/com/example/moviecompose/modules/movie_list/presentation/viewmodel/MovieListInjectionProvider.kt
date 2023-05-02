package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.movie_list.domain.interactore.GetMoviesByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListInjectionProvider @Inject constructor(
    val mainNavigationCoordinator: MainNavigationCoordinator,
    val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase
    ):
    ViewModel() {



    }

