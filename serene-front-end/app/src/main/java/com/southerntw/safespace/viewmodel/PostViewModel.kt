package com.southerntw.safespace.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.PostThreadResponse
import com.southerntw.safespace.data.preferences.SessionPreferences
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _userToken = mutableStateOf("")
    private val _userId = mutableStateOf("")

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

    private val _postThreadState = MutableStateFlow<AuthUiState<PostThreadResponse>>(AuthUiState.Idle)
    val postThreadState = _postThreadState.asStateFlow()

    fun postThread(title: String, text: String) {
        viewModelScope.launch {
            Log.d("TOKEN", _userToken.value)
            val tag = "defaultTag"  // Replace with actual tag logic if needed
            val threadStarter = "4287f6fe-1baf-4b09-bc59-fb7f6666b8be" // Dummy threadStarter ID

            repository.postThread(_userToken.value, title, text, tag, _userId.value).collectLatest { state ->
                _postThreadState.value = state
            }
        }
    }

    fun resetPostThreadState() {
        _postThreadState.value = AuthUiState.Idle
    }
}
