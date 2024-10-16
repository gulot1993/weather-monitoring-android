package com.weather.monitoring.app.features.register

import androidx.lifecycle.viewModelScope
import com.weather.monitoring.app.base.BaseViewModel
import com.weather.monitoring.app.data.domain.User
import com.weather.monitoring.app.data.domain.User.Companion.toEntity
import com.weather.monitoring.app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel<RegisterUIState>() {
    @Inject
    lateinit var userRepository: UserRepository
    override val initialState: RegisterUIState
        get() = RegisterUIState()

    fun register(
        username: String,
        password: String,
        firstname: String,
        lastname: String
    ) {
        viewModelScope.launch {
            try {
                mutableUIState.value = mutableUIState.value.copy(isLoading = true)
                delay(200)
                userRepository.registerUser(User(username, password, firstname, lastname).toEntity())
                delay(200)
                mutableUIState.value = mutableUIState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Throwable) {
                e.printStackTrace()
                mutableUIState.value = mutableUIState.value.copy(isLoading = false, error = e.message.orEmpty(), isSuccess = false)
            }
        }
    }
}