package com.example.moviecompose.modules.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.view.DotsIndicator
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel:HomeViewModel,
    callNextScreen:()->Unit
) {
    
    val uiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    val pagerState= rememberPagerState()
    val coroutineScope= rememberCoroutineScope()
    var currentPage= remember {
        0
    }
    LaunchedEffect(pagerState){
        snapshotFlow { pagerState.currentPage }.collect(){
            currentPage=it
        }
    }

    HorizontalPager(modifier = Modifier.fillMaxSize(),count = uiModel.value.screensCount, state = pagerState) { page ->
        MovieListScreen(currentPage=page,viewModel.mainNavigationCoordinator)


    }

}