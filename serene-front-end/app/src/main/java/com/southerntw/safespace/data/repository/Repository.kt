package com.southerntw.safespace.data.repository

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.southerntw.safespace.data.api.ANewsResponse
import com.southerntw.safespace.data.api.AuthResponse
import com.southerntw.safespace.data.api.BotChatResponses
import com.southerntw.safespace.data.api.BotEncourageResponses
import com.southerntw.safespace.data.api.CommentData
import com.southerntw.safespace.data.api.CommentsResponse
import com.southerntw.safespace.data.api.EditProfileResponse
import com.southerntw.safespace.data.api.NewsResponse
import com.southerntw.safespace.data.api.PostCommentResponse
import com.southerntw.safespace.data.api.PostThreadResponse
import com.southerntw.safespace.data.api.ProfileResponse
import com.southerntw.safespace.data.api.SafespaceApiService
import com.southerntw.safespace.data.api.ThreadResponse
import com.southerntw.safespace.data.pagingsource.NewsPagingSource
import com.southerntw.safespace.data.pagingsource.ThreadsPagingSource
import com.southerntw.safespace.data.preferences.SessionPreferences
import com.southerntw.safespace.util.AuthUiState
import com.southerntw.safespace.util.ChatUiState
import com.southerntw.safespace.util.UiState
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

    // Threads
    fun getThreads() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { ThreadsPagingSource(safespaceApiService) }
    ).flow

    fun getThread(threadId: Int): Flow<UiState<ThreadResponse>> {
        return flow {
            try {
                emit(UiState.Loading)
                val responseDetailGuide = safespaceApiService.getThread(threadId)
                emit(UiState.Success(responseDetailGuide))
            } catch (e: Exception) {
                emit(UiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun postThread(token: String, title: String, text: String, tag: String, threadStarter: String): Flow<AuthUiState<PostThreadResponse>> {
        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("text", text)
        jsonObject.put("tag", tag)
        jsonObject.put("threadStarter", threadStarter)

        val requestBody =
            jsonObject.toString().toRequestBody("application/json". toMediaTypeOrNull())

        return flow {
            try {
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                val responseThread = safespaceApiService.postThread("bearer $token", requestBody)
                emit(AuthUiState.Success(responseThread))
            } catch (e: Exception) {
                emit(AuthUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    // News
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { NewsPagingSource(safespaceApiService) }
    ).flow

    fun getNews(newsId: Int): Flow<UiState<ANewsResponse>> {
        return flow {
            try {
                emit(UiState.Loading)
                val responseDetailGuide = safespaceApiService.getANews(newsId)
                emit(UiState.Success(responseDetailGuide))
            } catch (e: Exception) {
                emit(UiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    // Auth
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
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                val responseLogin = safespaceApiService.login(requestBody)
                responseLogin.loginData?.let { loginData ->
                    sessionPreferences.startSession(true, loginData.token ?: "", loginData.id ?: "")
                    Log.d("Chat", "Login Token: ${loginData.token ?: ""}")
                }
                emit(AuthUiState.Success(responseLogin))
            } catch (e: Exception) {
                Log.d("Login", "Enters catch")
                emit(AuthUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProfile(userId: String): Flow<UiState<ProfileResponse>> {
        return flow {
            try {
                emit(UiState.Loading)
                val responseProfile = safespaceApiService.getProfile(userId)
                emit(UiState.Success(responseProfile))
            } catch (e: Exception) {
                emit(UiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun editProfile(token: String, id: String, name: String, email: String, password: String, avatar: String, about: String, birthdate: String, gender: String) : Flow<AuthUiState<EditProfileResponse>> {
        Log.d("Edit", "Repository 1")
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        jsonObject.put("avatar", avatar)
        jsonObject.put("about", about)
        jsonObject.put("birthdate", birthdate)
        jsonObject.put("gender", gender)

        val requestBody =
            jsonObject.toString().toRequestBody("application/json". toMediaTypeOrNull())

        Log.d("Edit", "Repository 2")

        return flow {
            try {
                Log.d("Edit", "Repository 3")
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                Log.d("Edit", "Repository 4")
                val responseProfile = safespaceApiService.editProfile("bearer $token", requestBody)
                Log.d("Edit", responseProfile.toString())
                emit(AuthUiState.Success(responseProfile))
            } catch (e: Exception) {
                emit(AuthUiState.Failure(e))
                Log.d("Edit", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun botChat(token: String, userId: String, message: String): Flow<ChatUiState<BotChatResponses>> {
        Log.d("Chat", "Repository1");
        val jsonObject = JSONObject().apply {
            put("userId", userId)
            put("message", message)
        }
        Log.d("Chat", jsonObject.toString());
        Log.d("Chat", token);

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

        return flow {
            try {
                Log.d("Chat", "Repository3");
                val response = safespaceApiService.botChat("Bearer $token", requestBody)
                emit(ChatUiState.Success(response))
                Log.d("Chat", "Repository4");
            } catch (e: Exception) {
                emit(ChatUiState.Failure(e))
                Log.e("Chat", e.toString());
            }
        }.flowOn(Dispatchers.IO)
    }

    fun botEncourage(token: String, userId: String): Flow<ChatUiState<BotEncourageResponses>> {
        val jsonObject = JSONObject().apply {
            put("userId", userId)
        }

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

        return flow {
            try {
                val response = safespaceApiService.botEncourage("Bearer $token", requestBody)
                emit(ChatUiState.Success(response))
            } catch (e: Exception) {
                emit(ChatUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getComments(threadId: Int): Flow<UiState<CommentsResponse>> {
        return flow {
            try {
                emit(UiState.Loading)
                val response = safespaceApiService.getComments(threadId)
                emit(UiState.Success(response))
            } catch (e: Exception) {
                emit(UiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    // modify this
    fun postComment(userId: String, threadId: Int, comment: String): Flow<AuthUiState<PostCommentResponse>> {
        val jsonObject = JSONObject().apply {
            put("userId", userId)
            put("threadId", threadId)
            put("comment", comment)
        }

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

        return flow {
            try {
                emit(AuthUiState.Idle)
                emit(AuthUiState.Load)
                val responseComment = safespaceApiService.postComment(requestBody)
                emit(AuthUiState.Success(responseComment))
            } catch (e: Exception) {
                emit(AuthUiState.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}