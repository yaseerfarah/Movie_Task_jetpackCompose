package com.example.moviecompose.modules.home.presentation.viewmodel

import android.content.Context
import android.util.Log
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.home.presentation.uimodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext context: Context,val mainNavigationCoordinator: MainNavigationCoordinator):
    StateViewModel<HomeUiModel, HomeUiState, HomeUIEffects,HomeUIEvents>(HomeUiState(tabList = listOf("First","Second","Third"))) {





    override fun mapStateToUIModel(oldState: HomeUiState, newState: HomeUiState): HomeUiModel {
        return  with(newState){
            HomeUiModel(
                tabList=tabList,
                errorMsg=errorMsg
            )
        }
    }

    override fun sendEvent(event: HomeUIEvents) {

    }

}