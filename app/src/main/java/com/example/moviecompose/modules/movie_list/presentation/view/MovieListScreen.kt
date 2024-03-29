package com.example.moviecompose.modules.movie_list.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.movie_list.presentation.cells.MovieItem
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListInjectionProvider
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListViewModel
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListViewModelFactory


@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(
    currentPage:Int,
    movieListInjectionProvider: MovieListInjectionProvider
) {

    val viewModel = viewModel<MovieListViewModel>(key = currentPage.toString(), factory = MovieListViewModelFactory(movieListInjectionProvider))
    val listUiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    Log.e("MovieListScreen","MovieListScreen >>>> ${viewModel.hashCode()}")
    LaunchedEffect(viewModel){
        viewModel.sendEvent(MovieListUIEvents.getData(currentPage))
    }



    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        columns = GridCells.Fixed(2)
    ){

        items(3){
            MovieItem(modifier = Modifier.fillMaxSize(), imageLink = listUiModel.value.currentData) {
                viewModel.sendEvent(MovieListUIEvents.navigateToDetailsScreen(it))
            }
        }

    }


}