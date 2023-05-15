package com.example.moviecompose.modules.movie_list.presentation.uimodel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
@Immutable
data class MovieListUiModel(val loading:Boolean,val currentData:List<MovieEntity>, val errorMsg: Int?=null)
