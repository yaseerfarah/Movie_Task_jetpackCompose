package com.example.moviecompose.modules.home.presentation.uimodel

import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents

sealed class HomeUIEvents{

data class OnPageChange(val page:Int): HomeUIEvents()

}