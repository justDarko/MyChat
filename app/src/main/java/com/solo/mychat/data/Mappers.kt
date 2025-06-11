package com.solo.mychat.data

import com.solo.mychat.data.local.entities.GoalEntity
import com.solo.mychat.domain.models.Goal

fun GoalEntity.toGoal() = Goal(
    id = id,
    goalTitle = goalTitle,
    achieved = achieved
)

fun Goal.toGoalEntity() = GoalEntity(
    id = id,
    goalTitle = goalTitle,
    achieved = achieved
)