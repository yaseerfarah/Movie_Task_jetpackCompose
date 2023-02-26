package com.example.moviecompose.base.presentations.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class StateViewModel<UIModel, UIState,EFFECT,EVENT>(
    val initialState: UIState,
) : ViewModel() {


    private var _effect: MutableStateFlow<EFFECT?> = MutableStateFlow(null)
    val uiModel = MutableStateFlow(mapStateToUIModel(initialState, initialState))
    val effect: StateFlow<EFFECT?> = _effect

    private var lockObject = Any()

    @Volatile
    protected var state: UIState = initialState
        @Synchronized private set(value) {
            synchronized(lockObject) {
                val oldVal = field
                field = value
                onStateUpdated(oldVal, value)
            }
        }






    protected open fun onStateUpdated(oldState: UIState, newState: UIState) {

        uiModel.value = mapStateToUIModel(oldState, newState)

    }

    protected open fun updateEffect(newEffect: EFFECT) {

        _effect.value = newEffect

    }

    protected abstract fun mapStateToUIModel(oldState: UIState, newState: UIState): UIModel

    abstract fun sendEvent(event:EVENT)

    fun resetEffect() {
        _effect.value=null
    }

    protected open fun updateState(stateTransformer: UIState.() -> UIState) {
        this.state = stateTransformer(state)
    }

    protected open fun updateState(state: UIState) {
        this.state = state
    }




}
