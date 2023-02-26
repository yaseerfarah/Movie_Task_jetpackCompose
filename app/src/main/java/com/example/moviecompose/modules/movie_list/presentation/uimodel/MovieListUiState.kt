package com.example.moviecompose.modules.home.presentation.uimodel

data class MovieListUiState(val screensCount:Int, val currentData:Int?=null, val loadContents:Boolean, val errorMsg: Int?=null)
