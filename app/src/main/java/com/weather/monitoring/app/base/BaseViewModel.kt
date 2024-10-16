package com.weather.monitoring.app.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<VS: Any> : ViewModel(){
    protected val mutableUIState: MutableStateFlow<VS> by lazy { MutableStateFlow(initialState) }
    abstract val initialState: VS
    val uiState: StateFlow<VS> by lazy { mutableUIState.asStateFlow() }
}