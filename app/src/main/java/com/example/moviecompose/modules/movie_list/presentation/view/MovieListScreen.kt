package com.example.moviecompose.modules.home.presentation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiModel
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListViewModel


@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(
    currentPage:Int,
    mainNavigationCoordinator: MainNavigationCoordinator
) {

    val viewModel = viewModel<MovieListViewModel>(key = currentPage.toString())
    val listUiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    Log.e("MovieListScreen","MovieListScreen >>>> ${viewModel.hashCode()}")
    LaunchedEffect(viewModel){
        viewModel.sendEvent(MovieListUIEvents.getData(currentPage))
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        listUiModel.value.currentData?.let {
            Image(painter = painterResource(id = it)
                , contentDescription ="MovieListScreen",
            )
        }

    }

}