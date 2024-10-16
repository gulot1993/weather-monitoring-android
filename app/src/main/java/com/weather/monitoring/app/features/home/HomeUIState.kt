package com.weather.monitoring.app.features.home

data class HomeUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
