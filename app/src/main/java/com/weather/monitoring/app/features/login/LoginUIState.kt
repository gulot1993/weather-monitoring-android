package com.weather.monitoring.app.features.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)
