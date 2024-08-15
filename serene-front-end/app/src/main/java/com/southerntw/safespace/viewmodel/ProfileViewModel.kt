package com.southerntw.safespace.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.ProfileResponse
import com.southerntw.safespace.data.api.ThreadResponse
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // Preferences
    private val _userId = mutableStateOf("")

    private val _userExist = mutableStateOf(false)
    val userExist: State<Boolean> get() = _userExist

    private val _userToken = mutableStateOf("")

    fun tryUserExist() {
        viewModelScope.launch {
            repository.getUserExist().collect {
                _userExist.value = it
            }
        }
    }

    fun tryUserToken() {
        viewModelScope.launch {
            repository.getUserToken().collect {
                _userToken.value = it
            }
        }
    }

    fun tryUserId() {
        viewModelScope.launch {
            repository.getUserId().collect {
                _userId.value = it
            }
        }
    }

    fun deleteSession() {
        viewModelScope.launch {
            repository.deleteSession()
        }
    }

    // Profile
    private val _profileResponse: MutableStateFlow<UiState<ProfileResponse>> = MutableStateFlow(
        UiState.Loading)
    val profileResponse: StateFlow<UiState<ProfileResponse>>
        get() = _profileResponse

    fun getUser() {
        viewModelScope.launch {
            repository.getProfile(_userId.value).collect {
                _profileResponse.value = it
            }
        }
    }

    private val _name = mutableStateOf("")
    val name: State<String> get() = _name

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _avatar = mutableStateOf("")
    val avatar: State<String> get() = _avatar

    private val _about = mutableStateOf("")
    val about: State<String> get() = _about

    private val _birthDate = mutableStateOf("")
    val birthDate: State<String> get() = _birthDate

    private val _gender = mutableStateOf("")
    val gender: State<String> get() = _gender

    // TODO: Make arguments nullable
    fun editProfile() {
        viewModelScope.launch {
            repository.editProfile(
                token = _userToken.value,
                _userId.value,
                _name.value,
                _email.value,
                _password.value,
                _avatar.value,
                _about.value,
                _birthDate.value,
                _gender.value
            )
        }
    }
}