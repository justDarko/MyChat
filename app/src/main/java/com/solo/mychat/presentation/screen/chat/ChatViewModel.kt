package com.solo.mychat.presentation.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.data.remote.requestModels.Message
import com.solo.mychat.domain.useCase.AskQuestionUseCase
import com.solo.mychat.domain.useCase.GetAllMessagesUseCase
import com.solo.mychat.presentation.screen.chat.ChatActions.LoadMessages
import com.solo.mychat.presentation.screen.chat.ChatActions.SendMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val askQuestionUseCase: AskQuestionUseCase,
    private val getAllMessagesUseCase: GetAllMessagesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ChatScreenViewState())
    val state = _state.asStateFlow()

    init {
        onAction(LoadMessages)
    }

    fun onAction(action: ChatActions) {
        when (action) {
            is SendMessage -> askQuestion(message = action.message)
            LoadMessages -> loadMessages()
        }
    }

    private fun askQuestion(message: String) {
        _state.update {
            it.copy(
                waitingOnResponse = true
            )
        }
        viewModelScope.launch {
            val result = askQuestionUseCase(
                AskQuestionUseCase.Params(
                    requestBody = ChatRequestBody(
                        listOf(
                            Message(
                                content = message
                            )
                        )
                    )
                )
            )
            when (result) {
                is CustomResult.Success -> {
                    _state.update {
                        it.copy(
                            waitingOnResponse = false
                        )
                    }
                }

                is CustomResult.Failure -> {}
            }
        }
    }

    private fun loadMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllMessagesUseCase.invoke().collectLatest { listOfMessages ->
                _state.update {
                    it.copy(
                        listOfMessages = listOfMessages
                    )
                }
            }
        }
    }
}