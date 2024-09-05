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

    @SerializedName("gender")
    var gender : String? = null,

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

    @SerializedName("gender")
    var gender : String? = null,

    @SerializedName("birthdate")
    var birthdate : String? = null
)

// Thread Responses
data class ThreadsResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var threadData : List<ThreadsDetail>,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class ThreadResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var threadData : ThreadData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

data class ThreadData (
   @SerializedName("thread")
   var threadDetail: ThreadsDetail,

   @SerializedName("comments")
   var comments: List<ThreadComments>
)

data class ThreadsDetail (
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("title")
    var title : String? = null,

    @SerializedName("text")
    var text : String? = null,

    @SerializedName("tag")
    var tag : String? = null,

    @SerializedName("threadStarter")
    var threadStarter : String? = null
)

data class ThreadComments (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("userId")
    var userId: String? = null,

    @SerializedName("threadId")
    var threadId: Int? = null,

    @SerializedName("userName")
    var userName: String? = null
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
    var writer : String? = null,

    @SerializedName("content")
    var content : String? = null,

    @SerializedName("category")
    var category : String? = null,

    @SerializedName("title")
    var title : String? = null,

    @SerializedName("thumbnail")
    var thumbnail : String? = null
)

data class ANewsResponse (
    @SerializedName("success")
    var success : Boolean? = null,

    @SerializedName("data")
    var newsData : NewsData? = null,

    @SerializedName("errors")
    var error : ErrorData? = null
)

// Bot Responses
data class BotChatResponses (
    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("data")
    var chatData: ChatData? = null,

    @SerializedName("errors")
    var error: ErrorData? = null
)

data class ChatData (
    @SerializedName("chat")
    var chatData: String? = null,

    @SerializedName("sentiment")
    var sentiment: Boolean? = null,
)

data class BotEncourageResponses (
    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("data")
    var data: EncourageData? = null,

    @SerializedName("errors")
    var error: ErrorData? = null
)

data class EncourageData (
    @SerializedName("message")
    var message: String? = null,

    @SerializedName("recommendations")
    var recommendations: List<NewsData>? = null
)

// Comment Response
data class CommentsResponse (
    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("data")
    var commentsData: List<CommentData>? = null
)

// Comment Data
data class CommentData (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("userId")
    var userId: String? = null,

    @SerializedName("threadId")
    var threadId: Int? = null,

    @SerializedName("userName")
    var userName: String? = null
)


// Post Comment Response
data class PostCommentResponse (
    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("data")
    var commentData: PostCommentData? = null
)

// Post Comment Data
data class PostCommentData (
    @SerializedName("comment")
    var comment: String? = null,

    @SerializedName("user_id")
    var userId: String? = null,

    @SerializedName("thread_id")
    var threadId: Int? = null
)