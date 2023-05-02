package com.example.moviecompose.modules.movie_list.presentation.uimodel

import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity

sealed class MovieListUIEvents{

    data class GetMoviesList(val categoryEntity:CategoryEntity): MovieListUIEvents()
    data class NavigateToDetailsScreen(val movieEntity: MovieEntity): MovieListUIEvents()

}