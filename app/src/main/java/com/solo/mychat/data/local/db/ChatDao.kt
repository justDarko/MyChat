package com.solo.mychat.data.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.solo.mychat.data.local.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM chat_tbl")
    fun getWholeConversation(): Flow<List<MessageEntity>>

    @Upsert
    suspend fun upsertNewMessage(messageEntity: MessageEntity)

}