package com.solo.mychat.domain.repository

import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun askQuestion(requestBody: ChatRequestBody): CustomResult<Boolean>
    fun getAllMessages(): Flow<List<Message>>
}