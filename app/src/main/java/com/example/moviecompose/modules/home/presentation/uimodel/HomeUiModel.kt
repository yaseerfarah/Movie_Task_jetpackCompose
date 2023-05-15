package com.example.moviecompose.modules.home.presentation.uimodel

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity

@Immutable
data class HomeUiModel( val loading:Boolean,val tabList:List<CategoryEntity>,@StringRes val errorMsg: Int?=null)
