package com.example.moviecompose.modules.home.presentation.uimodel

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity

data class HomeUiState(val loading:Boolean,
                       val tabList: List<CategoryEntity> = listOf(),
                       @StringRes val errorMsg: Int?=null)
