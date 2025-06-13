package com.solo.mychat.domain.useCase

import com.solo.mychat.domain.models.Message
import com.solo.mychat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : BaseUseCaseNoParams<Flow<List<Message>>>() {

    override operator fun invoke(): Flow<List<Message>> {
        return chatRepository.getAllMessages()
    }
}