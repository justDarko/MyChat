package com.solo.mychat.data.remote.api

import com.solo.mychat.data.remote.requestModels.ChatRequestBody
import com.solo.mychat.data.remote.dto.QuestionResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("matag2")
    suspend fun askQuestion(
        @Body chatRequestBody: ChatRequestBody
    ): QuestionResponseDTO
}