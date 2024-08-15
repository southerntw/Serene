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

    // Threads
    private val _threads = MutableStateFlow<PagingData<ThreadsData>>(PagingData.empty())
    val threads: StateFlow<PagingData<ThreadsData>> = _threads

    fun fetchThreads() {
        viewModelScope.launch {
            repository.getThreads()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _threads.value = pagingData
                }
        }
    }

    private val _threadsResponse: MutableStateFlow<UiState<ThreadResponse>> = MutableStateFlow(UiState.Loading)
    val threadResponse: StateFlow<UiState<ThreadResponse>> get() = _threadsResponse

    fun getDetailedThread(id: Int) {
        viewModelScope.launch {
            repository.getThread(id).collect {
                _threadsResponse.value = it
            }
        }
    }

    private val _postThreadResponse: MutableStateFlow<AuthUiState<PostThreadResponse>> = MutableStateFlow(AuthUiState.Idle)
    val postThreadResponse: StateFlow<AuthUiState<PostThreadResponse>> get() = _postThreadResponse

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

    // News
    private val _news = MutableStateFlow<PagingData<NewsData>>(PagingData.empty())
    val news: StateFlow<PagingData<NewsData>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            repository.getNews()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _news.value = pagingData
                }
        }
    }

    private val _newsResponse: MutableStateFlow<UiState<ANewsResponse>> = MutableStateFlow(UiState.Loading)
    val newsResponse: StateFlow<UiState<ANewsResponse>> get() = _newsResponse

    fun getDetailedNews(id: Int) {
        viewModelScope.launch {
            repository.getNews(id).collect {
                _newsResponse.value = it
            }
        }
    }
}