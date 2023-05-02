package com.example.moviecompose.modules.details.presentation.uimodel

import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity

data class DetailsUiState(val movieEntity: MovieEntity?, val movieCategories:List<CategoryEntity> = listOf(), val errorMsg: Int?=null)
