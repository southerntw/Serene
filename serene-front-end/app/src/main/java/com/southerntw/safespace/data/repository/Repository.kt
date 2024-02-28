package com.southerntw.safespace.data.repository

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.southerntw.safespace.data.api.AuthResponse
import com.southerntw.safespace.data.api.SafespaceApiService
import com.southerntw.safespace.data.pagingsource.NewsPagingSource
import com.southerntw.safespace.data.pagingsource.ThreadsPagingSource
import com.southerntw.safespace.data.preferences.SessionPreferences
import com.southerntw.safespace.util.AuthUiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class Repository @Inject constructor(
    private val safespaceApiService: SafespaceApiService,
    private val sessionPreferences: SessionPreferences,
    @ApplicationContext private val context: Context
) {
    fun getUserExist(): Flow<Boolean> {
        return sessionPreferences.getUserExist()
    }

    fun getUserToken(): Flow<String> {
        return sessionPreferences.getUserToken()
    }

    fun getUserId(): Flow<String> {
        return sessionPreferences.getUserId()
    }

    suspend fun deleteSession() {
        sessionPreferences.deleteSession()
    }

    fun getThreads() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { ThreadsPagingSource(safespaceApiService) }
    ).flow

    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { NewsPagingSource(safespaceApiService) }
    ).flow

    fun register(
        name: String,
        email: String,
        password: String
    ): Flow<AuthUiState<AuthResponse>> {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val requestBody =
            jsonObject.toString().toRequestBody("application/json". toMediaTypeOrNull())

        return flow {
            try {
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                val responseLogin = safespaceApiService.register(requestBody)
                emit(AuthUiState.Success(responseLogin))
            } catch (e: Exception) {
                emit(AuthUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun login(
        email: String,
        password: String
    ): Flow<AuthUiState<AuthResponse>> {
        Log.d("Login", "Enters Login Function")
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val requestBody =
            jsonObject.toString().toRequestBody("application/json". toMediaTypeOrNull())

        return flow {
            try {
                Log.d("Login", "Enters Try")
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                Log.d("Login", "Enters Load")
                val responseLogin = safespaceApiService.login(requestBody)
                Log.d("Login", "Enters Post-Load")
                responseLogin.loginData?.token?.let { sessionPreferences.startSession(true, it) }
                emit(AuthUiState.Success(responseLogin))
            } catch (e: Exception) {
                Log.d("Login", "Enters catch")
                emit(AuthUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}