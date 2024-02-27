package com.southerntw.safespace.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.AuthResponse
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // Preferences
    fun tryUserExist() {
        viewModelScope.launch {
            repository.getUserExist().collect {
                _userExist.value = it
            }
        }
    }

    private val _userExist = mutableStateOf(false)
    val userExist: State<Boolean> get() = _userExist

    // Register
    private val _registerResponse: MutableStateFlow<AuthUiState<AuthResponse>> =
        MutableStateFlow(AuthUiState.Idle)
    val registerResponse: StateFlow<AuthUiState<AuthResponse>>
        get() = _registerResponse

    fun register() {
        viewModelScope.launch {
            repository.register(nameRegister.value, emaiLRegister.value, passwordRegister.value).collect {
                _registerResponse.value = it
            }
        }
    }

    private val _nameRegister = mutableStateOf("")
    val nameRegister: State<String> get() = _nameRegister

    private val _emailRegister = mutableStateOf("")
    val emaiLRegister: State<String> get() = _emailRegister

    private val _passwordRegister = mutableStateOf("")
    val passwordRegister: State<String> get() = _passwordRegister

    private val _confirmPasswordRegister = mutableStateOf("")
    val confirmPasswordRegister: State<String> get() = _confirmPasswordRegister

    fun changeNameRegister(name: String) {
        _nameRegister.value = name
    }

    fun changeEmailRegister(email: String) {
        _emailRegister.value = email
    }

    fun changePasswordRegister(password: String) {
        _passwordRegister.value = password
    }

    fun changeConfirmPasswordRegister(confirmPassword: String) {
        _confirmPasswordRegister.value = confirmPassword
    }

    // Login
    private val _loginResponse: MutableStateFlow<AuthUiState<AuthResponse>> =
        MutableStateFlow(AuthUiState.Idle)
    val loginResponse: StateFlow<AuthUiState<AuthResponse>>
        get() = _loginResponse

    fun login() {
        viewModelScope.launch {
            repository.login(emaiLLogin.value, passwordLogin.value).collect {
                Log.d("Login", it.toString())
                _loginResponse.value = it
            }
        }
    }

    private val _emailLogin = mutableStateOf("")
    val emaiLLogin: State<String> get() = _emailLogin

    private val _passwordLogin = mutableStateOf("")
    val passwordLogin: State<String> get() = _passwordLogin

    fun changeEmailLogin(email: String) {
        _emailLogin.value = email
    }

    fun changePasswordLogin(password: String) {
        _passwordLogin.value = password
    }
}