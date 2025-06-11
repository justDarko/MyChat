package com.solo.mychat.domain.useCase

abstract class BaseUseCaseNoResult<in Params> {
    abstract suspend fun invoke(params: Params): Unit
}