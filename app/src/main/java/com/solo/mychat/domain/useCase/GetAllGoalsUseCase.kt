package com.solo.mychat.domain.useCase

import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.repository.GoalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) : BaseUseCaseNoParams<Flow<List<Goal>>>() {

    override operator fun invoke(): Flow<List<Goal>> {
        return goalsRepository.getListOfGoals()
    }
}