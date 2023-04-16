package com.example.moviecompose.modules.details.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.core.navigation.MainNavigationEvent
import com.example.moviecompose.modules.details.presentation.navigation.ARG_MOVIE_ID
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUIEffects
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUIEvents
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUiModel
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUiState
import com.example.moviecompose.modules.home.presentation.uimodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val mainNavigationCoordinator: MainNavigationCoordinator,
    savedStateHandle: SavedStateHandle, ):
    StateViewModel<DetailsUiModel, DetailsUiState, DetailsUIEffects,DetailsUIEvents>(DetailsUiState(imageUrl = savedStateHandle.get<String>(ARG_MOVIE_ID))) {





    override fun mapStateToUIModel(oldState: DetailsUiState, newState: DetailsUiState): DetailsUiModel {
        return  with(newState){
            DetailsUiModel(
                imageUrl=imageUrl,
                errorMsg=errorMsg
            )
        }
    }

    override fun sendEvent(event: DetailsUIEvents) {
        when(event){
            is DetailsUIEvents.BackToHomeScreen->{mainNavigationCoordinator.onEvent(
                MainNavigationEvent.NavigateBackToHome
            )}
        }
    }

}