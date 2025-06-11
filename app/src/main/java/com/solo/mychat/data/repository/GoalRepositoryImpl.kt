package com.solo.mychat.data.repository

import com.solo.mychat.data.local.db.GoalsDao
import com.solo.mychat.data.local.entities.GoalEntity
import com.solo.mychat.data.toGoal
import com.solo.mychat.data.toGoalEntity
import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.repository.GoalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalsDao: GoalsDao
) : GoalsRepository {
    override suspend fun addNewGoal(goalTitle: String, isAchieved: Boolean) {
        val goalEntity = GoalEntity(
            goalTitle = goalTitle,
            achieved = isAchieved
        )
        goalsDao.upsertNewGoal(goalEntity)
    }

    override fun getListOfGoals(): Flow<List<Goal>> {
        return goalsDao.getGoalsList().map { entities ->
            entities.map { it.toGoal() }
        }
    }

    override suspend fun completeGoal(goal: Goal) {
        goalsDao.updateGoal(goal.toGoalEntity())
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalsDao.deleteGoal(goal.toGoalEntity())
    }
}