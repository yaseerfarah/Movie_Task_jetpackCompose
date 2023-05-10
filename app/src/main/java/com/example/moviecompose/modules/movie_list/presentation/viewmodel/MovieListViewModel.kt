package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import android.util.Log
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.core.navigation.MainNavigationEvent
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEffects
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiModel
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val mainNavigationCoordinator: MainNavigationCoordinator,

):
    StateViewModel<MovieListUiModel, MovieListUiState, MovieListUIEffects, MovieListUIEvents>(MovieListUiState(screensCount = 3,
        currentData = null, loadContents = true
    )) {




    override fun sendEvent(event: MovieListUIEvents) {
        when(event){
            is MovieListUIEvents.getData->{getData(event.page) }
            is MovieListUIEvents.navigateToDetailsScreen->{mainNavigationCoordinator.onEvent(
                MainNavigationEvent.NavigateToDetailsScreen(event.imageLink)
            )}
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
                updateState(state.copy(currentData = "https://cdn.pixabay.com/photo/2019/03/03/20/23/background-4032775__340.png"))
            }

            1 -> {
                updateState(state.copy(currentData = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmlZY0r2C5TVF0CDxh-RkRcrTowRx-o4bKgA&usqp=CAU"))
            }

            2 -> {
                updateState(state.copy(currentData = "https://media.istockphoto.com/id/1064527936/vector/blue-ultraviolet-neon-curved-lines-abstract-background.jpg?s=612x612&w=0&k=20&c=DVl-_bo2HZU0tVqzp1FufxssO2BoPsRNb2W0LuzoEuc="))
            }
            else->{
                updateState(state.copy(currentData = "https://cdn.pixabay.com/photo/2019/03/03/20/23/background-4032775__340.png"))
            }
        }
        }
    }

