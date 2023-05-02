package com.example.moviecompose.modules.home.presentation.uimodel

import androidx.annotation.StringRes
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity

data class HomeUiModel( val loading:Boolean,val tabList:List<CategoryEntity>,@StringRes val errorMsg: Int?=null)
