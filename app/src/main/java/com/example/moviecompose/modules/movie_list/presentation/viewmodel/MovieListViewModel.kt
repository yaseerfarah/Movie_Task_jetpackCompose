package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import android.content.Context
import android.util.Log
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEffects
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiModel
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(@ApplicationContext context: Context):
    StateViewModel<MovieListUiModel, MovieListUiState, MovieListUIEffects,MovieListUIEvents>(MovieListUiState(screensCount = 3,
        currentData = null, loadContents = true
    )) {




    override fun sendEvent(event: MovieListUIEvents) {
        when(event){
            is MovieListUIEvents.getData->{getData(event.page) }
            else -> {}
        }
    }

    override fun mapStateToUIModel(oldState: MovieListUiState, newState: MovieListUiState): MovieListUiModel {
        return  with(newState){
            MovieListUiModel(
                screensCount=screensCount,
                currentData=currentData,
                loadContents=loadContents,
                errorMsg=errorMsg
            )
        }
    }

    private fun getData(currentScreen:Int){
        Log.e("currentScreen","currentScreen=$currentScreen")
        when(currentScreen){
            0 -> {
                updateState(state.copy(currentData = R.drawable.ic_launcher_background))
            }

            1 -> {
                updateState(state.copy(currentData = R.drawable.app_logo))
            }

            2 -> {
                updateState(state.copy(currentData = R.drawable.ic_launcher_background))
            }
        }
        }
    }

