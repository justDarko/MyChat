package com.solo.mychat.domain.repository

import com.solo.mychat.domain.models.Goal
import kotlinx.coroutines.flow.Flow

interface GoalsRepository {
    suspend fun addNewGoal(goalTitle: String, isAchieved: Boolean)
    fun getListOfGoals(): Flow<List<Goal>>
    suspend fun completeGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
}