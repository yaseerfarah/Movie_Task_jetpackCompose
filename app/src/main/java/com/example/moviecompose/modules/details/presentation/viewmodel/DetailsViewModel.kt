package com.example.moviecompose.modules.details.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.core.navigation.MainNavigationEvent
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
import com.example.moviecompose.modules.details.domain.interactor.GetMovieCategoriesUseCase
import com.example.moviecompose.modules.details.presentation.navigation.ARG_MOVIE_ID
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUIEffects
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUIEvents
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUiModel
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUiState
import com.example.moviecompose.modules.home.presentation.uimodel.*
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val mainNavigationCoordinator: MainNavigationCoordinator,
    val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,
    savedStateHandle: SavedStateHandle, ):
    StateViewModel<DetailsUiModel, DetailsUiState, DetailsUIEffects,DetailsUIEvents>(
        DetailsUiState(
            movieEntity = savedStateHandle.get<String>(ARG_MOVIE_ID).let { Gson().fromJson(it, MovieEntity::class.java) },
            errorMsg = if(savedStateHandle.get<String>(ARG_MOVIE_ID)==null) R.string.went_wrong else null
        )
    ) {


    init {
        state.movieEntity?.let {
            getMovieCategories(it)
        }
    }



    override fun mapStateToUIModel(oldState: DetailsUiState, newState: DetailsUiState): DetailsUiModel {
        return  with(newState){
            DetailsUiModel(
                movieEntity=movieEntity,
                movieCategories=movieCategories,
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

    private fun getMovieCategories(movieEntity: MovieEntity){
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val categories =  withContext<List<CategoryEntity>>(Dispatchers.IO){ getMovieCategoriesUseCase(movieEntity.genre_ids) }
                updateState(state.copy(movieCategories = categories))
            }catch (e:Throwable){
                updateState(state.copy(errorMsg = R.string.went_wrong))
            }

        }
    }

}