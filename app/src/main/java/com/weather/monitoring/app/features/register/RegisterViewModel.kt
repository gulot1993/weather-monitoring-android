package com.weather.monitoring.app.features.register

import com.weather.monitoring.app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel<RegisterUIState>() {
    override val initialState: RegisterUIState
        get() = RegisterUIState()
}