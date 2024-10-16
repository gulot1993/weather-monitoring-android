package com.weather.monitoring.app.features.register

data class RegisterUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
