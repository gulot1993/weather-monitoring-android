package com.weather.monitoring.app.features.history

import com.weather.monitoring.app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : BaseViewModel<HistoryUIState>() {
    override val initialState: HistoryUIState
        get() = HistoryUIState()
}