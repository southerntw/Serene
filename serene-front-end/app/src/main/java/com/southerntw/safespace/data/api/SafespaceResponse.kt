package com.southerntw.safespace.data.api

import com.google.gson.annotations.SerializedName

// Error Data
data class ErrorData (
    @SerializedName("message" )
    var message : String? = null,
)

// Auth Responses
data class AuthResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var loginData : LoginData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class RegisterData (
    @SerializedName("name" )
    var name : String? = null,

    @SerializedName("email")
    var email : String? = null
)

data class LoginData (
    @SerializedName("id" )
    var id : String? = null,

    @SerializedName("name" )
    var name : String? = null,

    @SerializedName("email")
    var email : String? = null,

    @SerializedName("access_token")
    var token : String? = null
)