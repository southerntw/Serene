package com.southerntw.safespace.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southerntw.safespace.data.api.BotChatResponses
import com.southerntw.safespace.data.model.ChatData
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.ChatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
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

    // Bot
    private val _chatResponses: MutableStateFlow<ChatUiState<BotChatResponses>> = MutableStateFlow(ChatUiState.Idle)
    val chatResponses: StateFlow<ChatUiState<BotChatResponses>>
        get() = _chatResponses

    private val _chatMsg = mutableStateOf("")
    val chatMsg: State<String> get() = _chatMsg

    private val _chatData = MutableStateFlow<List<com.southerntw.safespace.data.model.ChatData>>(emptyList())
    val chatData: StateFlow<List<com.southerntw.safespace.data.model.ChatData>> get() = _chatData

    fun updateChatMsg(newMsg: String) {
        _chatMsg.value = newMsg
    }

    fun addInitialMessage(initialMessage: String) {
        val botMessage = com.southerntw.safespace.data.model.ChatData(message = initialMessage, isUser = false)
        _chatData.value = _chatData.value + botMessage
    }

    fun sendMessage() {
        if (_chatMsg.value.isBlank()) return
        val userMessage = com.southerntw.safespace.data.model.ChatData(message = _chatMsg.value, isUser = true)
        _chatData.value = _chatData.value + userMessage

        viewModelScope.launch {
            _chatResponses.value = ChatUiState.Load
            val response = repository.botChat(_userToken.value, _userId.value, _chatMsg.value)
            response.collect { chatUiState ->
                if (chatUiState is ChatUiState.Success) {
                    chatUiState.data?.chatData?.chatData?.let { chatText ->
                        val botMessage = com.southerntw.safespace.data.model.ChatData(message = chatText, isUser = false)
                        _chatData.value = _chatData.value + botMessage
                    }
                }
                _chatResponses.value = chatUiState
            }
            _chatMsg.value = ""
        }
    }
}
