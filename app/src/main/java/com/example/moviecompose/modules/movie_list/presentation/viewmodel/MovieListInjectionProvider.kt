package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.core.navigation.MainNavigationEvent
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEffects
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiModel
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListInjectionProvider @Inject constructor(
    val mainNavigationCoordinator: MainNavigationCoordinator,

    ):
    ViewModel() {



    }

