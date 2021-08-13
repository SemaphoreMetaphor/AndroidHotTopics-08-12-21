package com.appnio.androidhottopics.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate


abstract class BaseViewModel<S : UiState, A: UiAction> : ViewModel() {
    protected val _actions = ConflatedBroadcastChannel<A>()
    protected val uiActions = _actions.asFlow().conflate()

    protected abstract val initialState: S
    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState


    protected fun setState(state: S) {
        _uiState.value = state
    }

    fun dispatch(action: A) {
        _actions.offer(action)
    }
}