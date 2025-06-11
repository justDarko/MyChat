package com.solo.mychat.presentation.screen.goal

import com.solo.mychat.domain.models.Goal

sealed interface GoalsActions {

    object LoadGoals : GoalsActions
    data class OpenAddGoalDialog(val open: Boolean) : GoalsActions

    //    data class EditGoal(val goalId: Int) : GoalsActions
    data class CompleteGoal(val goal: Goal) : GoalsActions
    data class DeleteGoal(val goal: Goal) : GoalsActions

    //data class AddGoalTitle(val goalTitle: String) : GoalsActions
    data class SaveGoal(val goalTitle: String, val isAchieved: Boolean) : GoalsActions
}