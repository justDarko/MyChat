package com.solo.mychat.domain.useCase

import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.repository.GoalsRepository
import javax.inject.Inject

class DeleteGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) : BaseUseCaseNoResult<DeleteGoalUseCase.Params>() {

    override suspend operator fun invoke(params: Params) {
        return goalsRepository.deleteGoal(
            goal = params.goal
        )
    }

    data class Params(
        val goal: Goal
    )
}