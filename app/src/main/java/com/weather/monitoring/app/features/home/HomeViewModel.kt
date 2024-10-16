package com.weather.monitoring.app.features.home

import com.weather.monitoring.app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUIState>() {
    override val initialState: HomeUIState
        get() = HomeUIState()
}