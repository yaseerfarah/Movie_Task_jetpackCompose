package com.example.moviecompose.modules.home.presentation.uimodel

sealed class HomeUIEvents{

data class OnPageChange(val page:Int):MovieListUIEvents()

}