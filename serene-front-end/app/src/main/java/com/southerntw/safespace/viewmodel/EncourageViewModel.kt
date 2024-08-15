package com.southerntw.safespace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.BotEncourageResponses
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.ChatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EncourageViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _userToken = MutableStateFlow("")
    private val _userId = MutableStateFlow("")
    private val _encourageResponses = MutableStateFlow<ChatUiState<BotEncourageResponses>>(ChatUiState.Idle)

    val encourageResponses: StateFlow<ChatUiState<BotEncourageResponses>> get() = _encourageResponses

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

    fun getEncouragement() {
        viewModelScope.launch {
            _encourageResponses.value = ChatUiState.Load
            val response = repository.botEncourage(_userToken.value, _userId.value)
            response.collect { encourageUiState ->
                _encourageResponses.value = encourageUiState
            }
        }
    }
}
