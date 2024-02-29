package com.southerntw.safespace.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.southerntw.safespace.data.api.ANewsResponse
import com.southerntw.safespace.data.api.NewsData
import com.southerntw.safespace.data.api.PostThreadResponse
import com.southerntw.safespace.data.api.ThreadResponse
import com.southerntw.safespace.data.api.ThreadsData
import com.southerntw.safespace.data.repository.Repository
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _userToken = mutableStateOf("")
    private val _userId = mutableStateOf("")

    // Threads
    fun getThreads(): Flow<PagingData<ThreadsData>> =
        repository.getThreads().cachedIn(viewModelScope)

    private val _threadsResponse: MutableStateFlow<UiState<ThreadResponse>> = MutableStateFlow(UiState.Loading)
    private val threadResponse: StateFlow<UiState<ThreadResponse>>
        get() = _threadsResponse

    fun getDetailedThread(id: Int) {
        viewModelScope.launch {
            repository.getThread(id).collect {
                _threadsResponse.value = it
            }
        }
    }

    private val _postThreadResponse: MutableStateFlow<AuthUiState<PostThreadResponse>> = MutableStateFlow(AuthUiState.Idle)
    private val postThreadResponse: StateFlow<AuthUiState<PostThreadResponse>>
        get() = _postThreadResponse

    private val _title = mutableStateOf("")
    val title: State<String> get() = _title

    private val _text = mutableStateOf("")
    val text: State<String> get() = _text

    private val _tag = mutableStateOf("")
    val tag: State<String> get() = _tag

    private val _threadStarter = mutableStateOf("")

    fun postThread() {
        viewModelScope.launch {
            repository.postThread(_userToken.value, _title.value, _text.value, _tag.value, _userId.value).collect {
                _postThreadResponse.value = it
            }
        }
    }

    // news
    fun getNews(): Flow<PagingData<NewsData>> =
        repository.getNews().cachedIn(viewModelScope)

    private val _newsResponse: MutableStateFlow<UiState<ANewsResponse>> = MutableStateFlow(UiState.Loading)
    private val newsResponse: StateFlow<UiState<ANewsResponse>>
        get() = _newsResponse

    fun getDetailedNews(id: Int) {
        viewModelScope.launch {
            repository.getNews(id).collect {
                _newsResponse.value = it
            }
        }
    }
}