package com.southerntw.safespace.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.southerntw.safespace.data.api.NewsData
import com.southerntw.safespace.data.api.ThreadsData
import com.southerntw.safespace.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ThreadsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getThreads(): Flow<PagingData<ThreadsData>> =
        repository.getThreads().cachedIn(viewModelScope)

    fun getNews(): Flow<PagingData<NewsData>> =
        repository.getNews().cachedIn(viewModelScope)
}