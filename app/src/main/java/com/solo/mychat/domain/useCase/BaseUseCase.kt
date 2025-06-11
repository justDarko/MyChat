package com.solo.mychat.domain.useCase

import com.solo.mychat.data.CustomResult

abstract class BaseUseCase<in Params, out Type> where Type : Any {
    abstract suspend fun invoke(params: Params):
            CustomResult<Type>

    suspend fun invoke(): CustomResult<Type> {
        return invoke(Unit as Params)
    }
}