package com.weather.monitoring.app.features.home

import androidx.lifecycle.viewModelScope
import com.weather.monitoring.app.base.BaseViewModel
import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.data.dto.WeatherForecastDTO.Companion.toDomain
import com.weather.monitoring.app.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUIState>() {
    @Inject
    lateinit var repository: WeatherRepository
    override val initialState: HomeUIState
        get() = HomeUIState()

    fun getWeatherForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
                repository
                    .getWeatherForecast(lat, lon)
                    .collectLatest {
                        when(it) {
                            is ResourceState.Error -> {
                                mutableUIState.value = mutableUIState.value.copy(error = it.message, isLoading = false)
                            }

                            is ResourceState.Loading -> {
                                mutableUIState.value = mutableUIState.value.copy(isLoading = true, error = "")
                            }

                            is ResourceState.Success -> {
                                mutableUIState.value = mutableUIState.value.copy(weather = it.data.toDomain(), isLoading = false, error = "")
                            }
                        }
                    }
            }
    }
}