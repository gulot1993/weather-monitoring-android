package com.weather.monitoring.app.features.login

import androidx.lifecycle.viewModelScope
import com.weather.monitoring.app.base.BaseViewModel
import com.weather.monitoring.app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
            userRepository
                .getUser(username, password)
                .onStart {
                    mutableUIState.value = mutableUIState.value.copy(isLoading = true)
                }
                .debounce(200)
                .catch {
                    mutableUIState.value = mutableUIState.value.copy(isLoading = false, error = it.message.orEmpty())
                }
                .collectLatest {
                    if (it != null) {
                        mutableUIState.value = mutableUIState.value.copy(isLoading = false, isSuccess = true, error = "")
                    } else {
                        mutableUIState.value = mutableUIState.value.copy(isLoading = false, error = "unknown user", isSuccess = false)
                    }
                }
        }
    }
}