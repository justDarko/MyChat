package com.solo.mychat.domain.useCase

abstract class BaseUseCaseNoParams<out Type> where Type : Any {
    abstract fun invoke(): Type
}