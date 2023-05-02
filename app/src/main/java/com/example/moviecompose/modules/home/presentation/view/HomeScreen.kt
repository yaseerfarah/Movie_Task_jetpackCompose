package com.example.moviecompose.modules.home.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    callNextScreen:()->Unit
) {
    
    val uiModel=viewModel.uiModel.collectAsStateWithLifecycle()
    val movieListInjectionProvider = hiltViewModel<MovieListInjectionProvider>()
    val pagerState= rememberPagerState()
    val coroutineScope= rememberCoroutineScope()
    val tabIndex = pagerState.currentPage

    LaunchedEffect(viewModel){
        viewModel.sendEvent(HomeUIEvents.GetAllCategories)
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
                   selectedTabIndex = tabIndex,
                   indicator = { tabPositions ->
                       TabRowDefaults.Indicator(
                           Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                           color = Color.White
                       )
                   },
                   containerColor = Color.Red,
                   edgePadding = 0.dp
               ) {
                   uiModel.value.tabList.forEachIndexed { index, pair ->
                       Tab(selected = tabIndex == index, onClick = {
                           coroutineScope.launch {
                               pagerState.animateScrollToPage(index)
                           }
                       }, text = {
                           Text(text = pair.name, color = Color.White)
                       })
                   }
               }

               HorizontalPager(modifier = Modifier.weight(weight = 1f),count = uiModel.value.tabList.count(), state = pagerState) { page ->
                   MovieListScreen(
                       categoryEntity = uiModel.value.tabList[page],
                       movieListInjectionProvider=movieListInjectionProvider
                   )
               }
           }


       }


   }





}