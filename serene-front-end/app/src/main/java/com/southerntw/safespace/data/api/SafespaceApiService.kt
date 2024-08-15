package com.southerntw.safespace.data.api

import okhttp3.RequestBody
import retrofit2.http.*

interface SafespaceApiService {
    // Auth
    @POST("auth/register")
    suspend fun register(@Body requestBody: RequestBody): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body requestBody: RequestBody): AuthResponse

    // Profile
    @PUT("user")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): EditProfileResponse

    @GET("user/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): ProfileResponse

    // Threads
    @GET("threads")
    suspend fun getThreads(
        @Query("page") page: Int
    ): ThreadsResponse

    @GET("thread/{id}")
    suspend fun getThread(
        @Path("id") id: Int
    ): ThreadResponse

    @POST("thread")
    suspend fun postThread(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): PostThreadResponse

    // News
    @GET("news")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse

    @GET("news/{id}")
    suspend fun getANews(
        @Path("id") id: Int
    ): ANewsResponse

    // Bot
    @POST("bot/chat")
    suspend fun botChat(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): BotChatResponses

    @POST("bot/encourage")
    suspend fun botEncourage(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): BotEncourageResponses
}