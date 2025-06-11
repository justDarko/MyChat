package com.solo.mychat.data.remote.requestModels

data class Message(
    val role: String = "user",
    val content: String
)
