package com.southerntw.safespace.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.EditProfileResponse
import com.southerntw.safespace.data.api.ProfileResponse
import com.southerntw.safespace.data.api.ThreadResponse
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.AuthUiState
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

    fun updateProfileData(
        name: String,
        about: String,
        birthDate: String,
        gender: String
    ) {
        _name.value = name
        _about.value = about
        _birthDate.value = birthDate
        _gender.value = gender
    }

    fun saveProfile() {
        viewModelScope.launch {
            editProfile()
        }
    }

    // Profile update method
    private fun editProfile() {
        Log.d("Edit", "Editing")
        viewModelScope.launch {
            repository.editProfile(
                token = _userToken.value,
                id = _userId.value,
                name = _name.value,
                email = _email.value,
                password = _password.value,
                avatar = _avatar.value,
                about = _about.value,
                birthdate = _birthDate.value,
                gender = _gender.value
            ).collect {
                _editResponse.value = it
            }
        }
    }

    // Profile
    private val _editResponse: MutableStateFlow<AuthUiState<EditProfileResponse>> = MutableStateFlow(
        AuthUiState.Idle)
    val editResponse: StateFlow<AuthUiState<EditProfileResponse>>
        get() = _editResponse
}