package com.example.moviecompose.modules.movie_list.presentation.uimodel

sealed class MovieListUIEvents{

    data class getData(val page:Int): MovieListUIEvents()
    data class navigateToDetailsScreen(val imageLink:String?): MovieListUIEvents()

}