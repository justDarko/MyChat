package com.solo.mychat.domain.useCase

import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.domain.repository.ChatRepository
import javax.inject.Inject

class AskQuestionUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : BaseUseCase<AskQuestionUseCase.Params, String>() {

    override suspend operator fun invoke(params: Params): CustomResult<String> {
        return chatRepository.askQuestion(requestBody = params.requestBody)
    }

    data class Params(
        val requestBody: ChatRequestBody
    )
}