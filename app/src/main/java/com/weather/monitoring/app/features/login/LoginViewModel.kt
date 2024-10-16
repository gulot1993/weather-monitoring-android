package com.weather.monitoring.app.features.login

import com.weather.monitoring.app.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel<LoginUIState>(){
    override val initialState: LoginUIState
        get() = LoginUIState()

}