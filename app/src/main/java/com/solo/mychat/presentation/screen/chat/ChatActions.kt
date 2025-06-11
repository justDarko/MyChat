package com.solo.mychat.presentation.screen.chat

sealed interface ChatActions {
    data class SendMessage(val message: String) : ChatActions
    object LoadMessages : ChatActions
}