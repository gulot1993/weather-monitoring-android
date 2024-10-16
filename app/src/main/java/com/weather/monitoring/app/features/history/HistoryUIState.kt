package com.weather.monitoring.app.features.history

data class HistoryUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
