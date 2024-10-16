package com.weather.monitoring.app.features.login

import androidx.lifecycle.viewModelScope
import com.weather.monitoring.app.base.BaseViewModel
import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.data.dto.WeatherForecastDTO.Companion.toDomain
import com.weather.monitoring.app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginUIState>(){
    @Inject
    lateinit var userRepository: UserRepository
    override val initialState: LoginUIState
        get() = LoginUIState()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                mutableUIState.value = mutableUIState.value.copy(isLoading = true, isSuccess = false, error = "")
                delay(200)
                val user = userRepository.getUser(username, password)
                delay(200)
                if (user == null) {
                    mutableUIState.value = mutableUIState.value.copy(isLoading = false, error = "Username or password is incorrect", isSuccess = false)
                } else {
                    mutableUIState.value = mutableUIState.value.copy(isLoading = false, error = "", isSuccess = true)
                }

            } catch (e: Throwable) {
                mutableUIState.value =  mutableUIState.value.copy(isLoading = false, error = e.message.toString())
            }
        }
    }
}