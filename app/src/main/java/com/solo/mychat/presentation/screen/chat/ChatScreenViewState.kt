package com.solo.mychat.presentation.screen.chat

import com.solo.mychat.domain.models.Message

data class ChatScreenViewState(
    val listOfMessages: List<Message> = emptyList(),
    val waitingOnResponse: Boolean = false
)
