package com.example.moviecompose.modules.splash.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUIEffects
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUIEvents
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUiModel
import com.example.moviecompose.modules.splash.presentation.uimodel.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(@ApplicationContext context: Context):
    StateViewModel<SplashUiModel,SplashUiState,SplashUIEffects,SplashUIEvents>(SplashUiState()) {



    override fun mapStateToUIModel(
        oldState: SplashUiState,
        newState: SplashUiState
    ): SplashUiModel {
        return SplashUiModel(
            passToNextScreen = newState.passToNextScreen,
            errorMsg = newState.errorMsg
        )
    }

    override fun sendEvent(event: SplashUIEvents) {
        when(event){
            is SplashUIEvents.StartTimer->startTimer()
        }
    }


   private fun startTimer(){
        viewModelScope.launch {
            delay(2000)
            updateState(SplashUiState(passToNextScreen = true))
            updateEffect(SplashUIEffects.PassToNextScreen)
        }
    }



}