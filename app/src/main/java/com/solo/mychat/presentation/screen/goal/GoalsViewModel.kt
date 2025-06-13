package com.solo.mychat.presentation.screen.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.useCase.CompleteGoalUseCase
import com.solo.mychat.domain.useCase.DeleteGoalUseCase
import com.solo.mychat.domain.useCase.GetAllGoalsUseCase
import com.solo.mychat.domain.useCase.UpsertGoalUseCase
import com.solo.mychat.presentation.screen.goal.GoalsActions.CompleteGoal
import com.solo.mychat.presentation.screen.goal.GoalsActions.DeleteGoal
import com.solo.mychat.presentation.screen.goal.GoalsActions.LoadGoals
import com.solo.mychat.presentation.screen.goal.GoalsActions.OpenAddGoalDialog
import com.solo.mychat.presentation.screen.goal.GoalsActions.SaveGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
    private val upsertGoalUseCase: UpsertGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val completeGoalUseCase: CompleteGoalUseCase
) : ViewModel() {

    init {
        onAction(LoadGoals)
    }

    private val _state = MutableStateFlow(GoalScreenViewState())
    val state = _state.asStateFlow()

    fun onAction(action: GoalsActions) {
        when (action) {
            is CompleteGoal -> completeGoal(action.goal)
            is DeleteGoal -> deleteGoal(action.goal)
            LoadGoals -> {
                Timber.d("Room fetch ...")
                getGoalsList()
            }

            is OpenAddGoalDialog -> setGoalDialogPresence(showDialog = action.open)
            is SaveGoal -> {
                addNewGoal(
                    goalTitle = action.goalTitle,
                    isAchieved = action.isAchieved
                )
            }
        }
    }

    private fun getGoalsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllGoalsUseCase.invoke().collectLatest { goalsList ->
                Timber.d("The goasl are: $goalsList")
                _state.update {
                    it.copy(
                        listOfGoals = goalsList
                    )
                }
            }
        }
    }

    private fun setGoalDialogPresence(showDialog: Boolean) {
        _state.update {
            it.copy(
                showAddGoalDialog = showDialog
            )
        }
    }

    private fun addNewGoal(goalTitle: String, isAchieved: Boolean) {
        if (goalTitle.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                upsertGoalUseCase(
                    UpsertGoalUseCase.Params(
                        goalTitle = goalTitle,
                        achieved = isAchieved
                    )
                )
            }
        }
    }

    private fun completeGoal(goal: Goal) {
        Timber.d("Completing the goal with id: ${goal.id}")
        viewModelScope.launch(Dispatchers.IO) {
            completeGoalUseCase(CompleteGoalUseCase.Params(goal))
        }
    }

    private fun deleteGoal(goal: Goal) {
        Timber.d("Deleting the goal with id: ${goal.id}")
        viewModelScope.launch(Dispatchers.IO) {
            deleteGoalUseCase(DeleteGoalUseCase.Params(goal))
        }
    }
}