package com.solo.mychat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solo.mychat.data.local.entities.GoalEntity

@Database(entities = [GoalEntity::class], version = 1)
abstract class GoalsDatabase : RoomDatabase() {
    abstract fun goalsDao(): GoalsDao

}