package com.example.moviecompose.modules.home.presentation.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.view.LoadingUiState
import com.example.moviecompose.modules.home.presentation.uimodel.HomeUIEvents
import com.example.moviecompose.modules.home.presentation.viewmodel.HomeViewModel
import com.example.moviecompose.modules.movie_list.presentation.view.MovieListScreen
import com.example.moviecompose.modules.movie_list.presentation.viewmodel.MovieListInjectionProvider
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: HomeViewModel=hiltViewModel<HomeViewModel>()
) {
    
    val uiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    val movieListInjectionProvider = hiltViewModel<MovieListInjectionProvider>()
    val pagerState= rememberPagerState()
    val coroutineScope= rememberCoroutineScope()


    LaunchedEffect(key1 =viewModel){
        viewModel.sendEvent(HomeUIEvents.GetAllCategories)
    }
    DisposableEffect(key1 = viewModel){
        Log.e("HomeScreenView","${viewModel.hashCode()}")

        onDispose {
            Log.e("HomeScreenView","onDispose >>>> ${viewModel.hashCode()}")
        }
    }


   Scaffold(
       modifier = Modifier.fillMaxSize(),
       topBar = {
           CenterAlignedTopAppBar(
               title = {
                   Text(text = LocalContext.current.getString(R.string.app_name))
               },
               colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)


               )
       }

   ) {innerPadding->
       if (uiModel.value.errorMsg!=null)
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(innerPadding)){
               Text(
                   modifier = Modifier.align(Alignment.Center),
                   text = LocalContext.current.getString(uiModel.value.errorMsg!!),
                   color=Color.Red)
           }
       else

       LoadingUiState(modifier =
       Modifier
           .fillMaxSize()
           .padding(innerPadding),
           isLoading = uiModel.value.loading ) {

           Column(modifier = Modifier
               .fillMaxSize()
               ) {



               ScrollableTabRow(
                   selectedTabIndex = pagerState.currentPage,
                   indicator = { tabPositions ->
                       TabRowDefaults.Indicator(
                           Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                           color = Color.White
                       )
                   },
                   containerColor = Color.Red,
                   edgePadding = 0.dp
               ) {
                   uiModel.value.tabList.forEachIndexed { index, pair ->
                       Tab(selected = pagerState.currentPage == index, onClick = {
                           coroutineScope.launch {
                               pagerState.animateScrollToPage(index)
                           }
                       }, text = {
                           Text(text = pair.name, color = Color.White)
                       })
                   }
               }

               HorizontalPager(
                   modifier = Modifier.weight(weight = 1f),
                   pageCount = uiModel.value.tabList.count(),
                   state = pagerState, key = {uiModel.value.tabList[it].id},
                   beyondBoundsPageCount = 0
               ) { page ->
                   MovieListScreen(
                       categoryEntity = uiModel.value.tabList[page],
                       movieListInjectionProvider=movieListInjectionProvider
                   )
               }
           }


       }


   }





}