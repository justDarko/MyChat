package com.solo.mychat.data.repository

import com.solo.mychat.data.CustomResult
import com.solo.mychat.data.remote.api.ApiService
import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ChatRepository {
    override suspend fun askQuestion(requestBody: ChatRequestBody): CustomResult<String> {
        val response = apiService.askQuestion(requestBody)
        return if (response.status)
            CustomResult.Success(response.result)
        else CustomResult.Failure("Error answering the question")
    }
}