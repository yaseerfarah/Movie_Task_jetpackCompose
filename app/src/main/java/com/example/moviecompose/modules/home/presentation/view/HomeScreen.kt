package com.example.moviecompose.modules.home.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.view.DotsIndicator
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    val tabIndex = pagerState.currentPage


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
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(innerPadding)) {



           TabRow(
               selectedTabIndex = tabIndex,
               indicator = { tabPositions ->
                   TabRowDefaults.Indicator(
                       Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                       color = Color.White
                   )
               },
               containerColor = Color.Red
           ) {
               uiModel.value.tabList.forEachIndexed { index, pair ->
                   Tab(selected = tabIndex == index, onClick = {
                       coroutineScope.launch {
                           pagerState.animateScrollToPage(index)
                       }
                   }, text = {
                       Text(text = pair, color = Color.White)
                   })
               }
           }

           HorizontalPager(modifier = Modifier.weight(weight = 1f),count = uiModel.value.tabList.count(), state = pagerState) { page ->
               MovieListScreen(currentPage=page,viewModel.mainNavigationCoordinator)
           }
       }

   }





}