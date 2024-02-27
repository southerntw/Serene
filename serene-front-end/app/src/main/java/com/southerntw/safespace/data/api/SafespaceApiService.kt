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
    @PUT("profile")
    suspend fun editProfile(@Body requestBody: RequestBody): EditProfileResponse

    @POST("profile/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): ProfileResponse

    // Threads
    @GET("threads")
    suspend fun getThreads(): ThreadsResponse

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
    suspend fun getNews(): NewsResponse

    @GET("news/{id}")
    suspend fun getANews(
        @Path("id") id: Int
    ): ANewsResponse
}