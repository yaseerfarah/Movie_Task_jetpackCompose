package com.example.moviecompose.modules.details.presentation.uimodel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
@Immutable
data class DetailsUiModel(val movieEntity: MovieEntity?, val movieCategories:List<CategoryEntity>, val errorMsg: Int?=null)
