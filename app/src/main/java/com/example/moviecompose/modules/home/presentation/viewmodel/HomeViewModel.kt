package com.example.moviecompose.modules.home.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.viewmodel.StateViewModel
import com.example.moviecompose.core.navigation.MainNavigationCoordinator
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.home.domain.interactor.GetAllCategoriesUseCase
import com.example.moviecompose.modules.home.presentation.uimodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val mainNavigationCoordinator: MainNavigationCoordinator
    ,private val getAllCategoriesUseCase: GetAllCategoriesUseCase):
    StateViewModel<HomeUiModel, HomeUiState, HomeUIEffects,HomeUIEvents>(HomeUiState(loading = true)) {





    override fun mapStateToUIModel(oldState: HomeUiState, newState: HomeUiState): HomeUiModel {
        return  with(newState){
            HomeUiModel(
                loading=loading,
                tabList=tabList,
                errorMsg=errorMsg
            )
        }
    }

    override fun sendEvent(event: HomeUIEvents) {
        when(event){
            HomeUIEvents.GetAllCategories->getAllCategories()
        }
    }


    private fun getAllCategories(){
        updateState(state.copy(loading = true))
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val categories =  withContext<List<CategoryEntity>>(Dispatchers.IO){ getAllCategoriesUseCase(Unit) }
                updateState(state.copy(loading = false,tabList = categories))
            }catch (e:Throwable){
                updateState(state.copy(loading = false, errorMsg = R.string.went_wrong))
            }

        }
    }

}