package com.southerntw.safespace.data.api

import okhttp3.RequestBody
import retrofit2.http.*

interface SafespaceApiService {
    @POST("auth/register")
    suspend fun register(@Body requestBody: RequestBody): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body requestBody: RequestBody): AuthResponse
}