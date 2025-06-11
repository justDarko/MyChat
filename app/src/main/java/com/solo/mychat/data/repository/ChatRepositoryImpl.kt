package com.solo.mychat.data.repository

import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.local.db.ChatDao
import com.solo.mychat.data.local.entities.MessageEntity
import com.solo.mychat.data.remote.api.ApiService
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.data.toMassage
import com.solo.mychat.domain.models.Message
import com.solo.mychat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val chatDao: ChatDao
) : ChatRepository {
    override suspend fun askQuestion(requestBody: ChatRequestBody): CustomResult<Boolean> {
        val message = requestBody.messages[0]
        val messageEntity = MessageEntity(
            text = message.content,
            isFromMe = true
        )
        chatDao.upsertNewMessage(messageEntity)
        val response = apiService.askQuestion(requestBody)
        val messageResponse = MessageEntity(
            text = response.result,
            isFromMe = false
        )
        chatDao.upsertNewMessage(messageResponse)
        return CustomResult.Success(response.result.isNotBlank())
    }

    override fun getAllMessages(): Flow<List<Message>> {
        return chatDao.getWholeConversation().map { entities ->
            entities.map { it.toMassage() }
        }
    }
}