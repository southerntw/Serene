package com.southerntw.safespace.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // Preferences
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

    private val _userExist = mutableStateOf(false)
    val userExist: State<Boolean> get() = _userExist

    private val _userToken = mutableStateOf("")

    fun deleteSession() {
        viewModelScope.launch {
            repository.deleteSession()
        }
    }

    // Profile

}