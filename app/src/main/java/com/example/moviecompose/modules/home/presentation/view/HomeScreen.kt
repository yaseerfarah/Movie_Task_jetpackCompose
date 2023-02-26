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

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Movie Compose")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                ),
            )
        },
        content = {
            Column (modifier = Modifier
                .fillMaxSize()
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally){
                HorizontalPager(modifier = Modifier.weight(1f),count = uiModel.value.screensCount, state = pagerState) { page ->
                    when(currentPage){
                        0->{MovieListScreen(currentPage=currentPage) {
                            coroutineScope.launch {
                                callNextScreen()
                            }
                        }}
                        1->{MovieListScreen(currentPage=currentPage) {
                            coroutineScope.launch {
                                callNextScreen()
                            }
                        }}
                        2->{MovieListScreen(currentPage=currentPage) {
                            coroutineScope.launch {
                                callNextScreen()
                            }
                        }}
                    }


                }

                Spacer(modifier = Modifier
                    .weight(0.1f)
                    .padding(4.dp))

                DotsIndicator(
                    modifier = Modifier.weight(0.1f),
                    totalDots = 3,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = Color.White,
                    unSelectedColor = Color.Gray
                )
            }
            }

    )

}