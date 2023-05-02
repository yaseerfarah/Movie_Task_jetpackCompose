package com.example.moviecompose.modules.movie_list.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.core.navigation.MainNavigationEvent
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
import com.example.moviecompose.modules.home.presentation.uimodel.MovieListUIEffects
import com.example.moviecompose.modules.movie_list.domain.interactore.GetMoviesByCategoryUseCase
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUIEvents
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUiModel
import com.example.moviecompose.modules.movie_list.presentation.uimodel.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val mainNavigationCoordinator: MainNavigationCoordinator,
    private val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase,

):
    StateViewModel<MovieListUiModel, MovieListUiState, MovieListUIEffects, MovieListUIEvents>(
        MovieListUiState(loading = true)
    ) {




    override fun sendEvent(event: MovieListUIEvents) {
        when(event){
            is MovieListUIEvents.GetMoviesList->{getMoviesList(event.categoryEntity) }
            is MovieListUIEvents.NavigateToDetailsScreen->{mainNavigationCoordinator.onEvent(MainNavigationEvent.NavigateToDetailsScreen(event.movieEntity))}

        }
    }

    override fun mapStateToUIModel(oldState: MovieListUiState, newState: MovieListUiState): MovieListUiModel {
        return  with(newState){
            MovieListUiModel(
                loading=loading,
                currentData=currentData,
                errorMsg=errorMsg
            )
        }
    }

    private fun getMoviesList(categoryEntity:CategoryEntity){

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val moviesList =  withContext<List<MovieEntity>>(Dispatchers.IO){ getMoviesByCategoryUseCase(categoryEntity).moviesList }
                updateState(state.copy(loading = false, currentData = moviesList))
            }catch (e:Throwable){
                updateState(state.copy(loading = false, errorMsg = R.string.went_wrong))
            }

        }

        }
    }

