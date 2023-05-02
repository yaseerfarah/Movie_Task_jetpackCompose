package com.example.moviecompose.modules.movie_list.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviecompose.base.presentations.view.LoadingUiState
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.movie_list.presentation.cells.MovieItem
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListInjectionProvider
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListViewModel
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListViewModelFactory


@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(
    categoryEntity: CategoryEntity,
    movieListInjectionProvider: MovieListInjectionProvider
) {

    val viewModel = viewModel<MovieListViewModel>(key = categoryEntity.id.toString(), factory = MovieListViewModelFactory(movieListInjectionProvider))
    val listUiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    Log.e("MovieListScreen","MovieListScreen >>>> ${viewModel.hashCode()}")
    LaunchedEffect(viewModel){
        viewModel.sendEvent(MovieListUIEvents.GetMoviesList(categoryEntity))
    }


    listUiModel.value.errorMsg?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = LocalContext.current.getString(it),
                color=Color.Red)
        }
    }
        ?:

        LoadingUiState(
            modifier = Modifier.fillMaxSize().background(Color.White),
            isLoading = listUiModel.value.loading) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                columns = GridCells.Fixed(2)
            ){
                items(listUiModel.value.currentData.size, key = {listUiModel.value.currentData[it].id}){index->
                    MovieItem(
                        modifier = Modifier.fillMaxSize(),
                        movieEntity = listUiModel.value.currentData[index]

                    ) {
                        viewModel.sendEvent(MovieListUIEvents.NavigateToDetailsScreen(it))
                    }
                }
        }


    }


}