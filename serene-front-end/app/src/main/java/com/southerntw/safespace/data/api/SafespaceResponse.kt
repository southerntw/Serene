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

// Profile Responses
data class ProfileResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var profileData : ProfileData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class ProfileData (
    @SerializedName("id" )
    var id : String? = null,

    @SerializedName("name" )
    var name : String? = null,

    @SerializedName("email")
    var email : String? = null,

    @SerializedName("avatar")
    var avatar : String? = null,

    @SerializedName("about")
    var about : String? = null,

    @SerializedName("birthdate")
    var birthdate : String? = null
)

data class EditProfileResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var profileData : EditProfileData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class EditProfileData (
    @SerializedName("id" )
    var id : String? = null,

    @SerializedName("name" )
    var name : String? = null,

    @SerializedName("email")
    var email : String? = null,

    @SerializedName("avatar")
    var avatar : String? = null,

    @SerializedName("about")
    var about : String? = null,

    @SerializedName("birthdate")
    var birthdate : String? = null
)

// Thread Responses
data class ThreadsResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var threadData : List<ThreadsData>,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class ThreadResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var threadData : ThreadsData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class ThreadsData (
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("text")
    var text : String? = null,

    @SerializedName("tag")
    var tag : String? = null,

    @SerializedName("threadStarter")
    var threadStarter : String? = null
)

data class PostThreadResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var threadData : PostThreadData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class PostThreadData (
    @SerializedName("text")
    var text : String? = null,

    @SerializedName("tag")
    var tag : String? = null,

    @SerializedName("threadStarter")
    var threadStarter : String? = null
)

// News Responses
data class NewsResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var newsData : List<NewsData>,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class NewsData (
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("writer")
    var text : String? = null,

    @SerializedName("content")
    var tag : String? = null,

    @SerializedName("category")
    var threadStarter : String? = null
)

data class ANewsResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var newsData : NewsData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)