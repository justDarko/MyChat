package com.solo.mychat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solo.mychat.data.local.entities.GoalEntity
import com.solo.mychat.data.local.entities.MessageEntity

@Database(entities = [GoalEntity::class, MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalsDao(): GoalsDao
    abstract fun chatDao(): ChatDao

}