package com.solo.mychat.presentation.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.data.remote.requestModels.Message
import com.solo.mychat.domain.useCase.AskQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val askQuestionUseCase: AskQuestionUseCase
) : ViewModel() {

    fun askQuestion(message: String) {
        viewModelScope.launch {
            askQuestionUseCase(
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
        }
    }
}