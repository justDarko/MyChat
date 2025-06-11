package com.solo.mychat.domain.useCase

import com.solo.mychat.domain.repository.GoalsRepository
import javax.inject.Inject

class UpsertGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) : BaseUseCaseNoResult<UpsertGoalUseCase.Params>() {

    override suspend operator fun invoke(params: Params) {
        return goalsRepository.addNewGoal(
            goalTitle = params.goalTitle,
            isAchieved = params.achieved
        )
    }

    data class Params(
        val goalTitle: String,
        val achieved: Boolean
    )
}