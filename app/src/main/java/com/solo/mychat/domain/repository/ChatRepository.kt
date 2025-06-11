package com.solo.mychat.domain.repository

import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.remote.requestModels.ChatRequestBody

interface ChatRepository {
    suspend fun askQuestion(requestBody: ChatRequestBody): CustomResult<String>
}