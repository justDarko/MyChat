package com.solo.mychat.domain.useCase

import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.repository.GoalsRepository
import javax.inject.Inject

class CompleteGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) : BaseUseCaseNoResult<CompleteGoalUseCase.Params>() {

    override suspend operator fun invoke(params: Params) {
        val achieved = params.goal.achieved
        val goal = params.goal.copy(
            achieved = !achieved
        )
        return goalsRepository.completeGoal(
            goal = goal
        )
    }

    data class Params(
        val goal: Goal
    )
}