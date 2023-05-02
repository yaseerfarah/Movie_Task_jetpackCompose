package com.example.moviecompose.modules.movie_list.presentation.uimodel

import com.example.moviecompose.modules.core.domain.entity.MovieEntity

data class MovieListUiModel(val loading:Boolean,val currentData:List<MovieEntity>, val errorMsg: Int?=null)
