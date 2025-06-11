package com.solo.mychat.presentation.screen.goal

import com.solo.mychat.domain.models.Goal

data class GoalScreenViewState(
    val listOfGoals: List<Goal> = emptyList(),
    val showAddGoalDialog: Boolean = false,
)
