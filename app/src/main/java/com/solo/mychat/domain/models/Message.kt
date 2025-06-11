package com.solo.mychat.domain.models

data class Message(
    val id: String,
    val text: String,
    val isFromMe: Boolean
)
