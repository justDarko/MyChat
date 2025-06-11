package com.solo.mychat.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.solo.mychat.data.local.entities.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalsDao {

    @Query("SELECT * FROM goals_tbl")
    fun getGoalsList(): Flow<List<GoalEntity>>

    @Upsert
    suspend fun upsertNewGoal(goalEntity: GoalEntity)

    @Update
    suspend fun updateGoal(goalEntity: GoalEntity)

    @Delete
    suspend fun deleteGoal(goalEntity: GoalEntity)

}