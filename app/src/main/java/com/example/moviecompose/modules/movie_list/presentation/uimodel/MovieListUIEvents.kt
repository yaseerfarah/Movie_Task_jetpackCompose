package com.example.moviecompose.modules.home.presentation.uimodel

sealed class MovieListUIEvents{

data class getData(val page:Int):MovieListUIEvents()

}