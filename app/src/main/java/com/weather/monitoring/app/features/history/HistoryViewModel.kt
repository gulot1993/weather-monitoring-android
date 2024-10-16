package com.weather.monitoring.app.features.history

import androidx.lifecycle.viewModelScope
import com.weather.monitoring.app.base.BaseViewModel
import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : BaseViewModel<HistoryUIState>() {
    @Inject
    lateinit var repository: WeatherRepository
    override val initialState: HistoryUIState
        get() = HistoryUIState()

    fun getHistoryWeatherForecast() {
        viewModelScope.launch {
            repository
                .getAllWeatherForecasts()
                .collectLatest {
                    when(it) {
                        is ResourceState.Error -> {
                            mutableUIState.value = mutableUIState.value.copy(error = it.message, isLoading = false)
                        }

                        is ResourceState.Loading -> {
                            mutableUIState.value = mutableUIState.value.copy(isLoading = true, error = "")
                        }

                        is ResourceState.Success -> {
                            mutableUIState.value = mutableUIState.value.copy(weatherForecasts = it.data, isLoading = false, error = "")
                        }
                    }
                }
        }
    }
}