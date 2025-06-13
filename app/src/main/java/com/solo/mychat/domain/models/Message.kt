package com.solo.mychat.domain.models

data class Message(
    val id: Int,
    val text: String,
    val isFromMe: Boolean
)
