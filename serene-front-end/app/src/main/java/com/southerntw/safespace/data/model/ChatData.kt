package com.southerntw.safespace.data.model

data class ChatData(
    val message: String = "",
    val isUser: Boolean = false
)

object ChatManager {
    private val chatContent = mutableListOf<ChatData>()

    fun addChat(chat: ChatData) {
        chatContent.add(chat)
    }

    fun getAllChat(): List<ChatData> {
        return chatContent.toList()
    }
}