package com.solo.mychat.presentation.screen.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.mychat.domain.useCase.GetAllGoalsUseCase
import com.solo.mychat.presentation.screen.stats.StatsActions.GetGoals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(StatsViewState())
    val state = _state.asStateFlow()

    init {
        onAction(GetGoals)
    }

    private fun onAction(action: StatsActions) {
        when (action) {
            GetGoals -> getGoalsList()
        }
    }

    private fun getGoalsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllGoalsUseCase().collectLatest { goals ->
                _state.update {
                    it.copy(
                        numberOfGoals = goals.size,
                        numberOfAchievedGoals = goals.filter { goal -> goal.achieved }.size
                    )
                }
            }
        }
    }
}