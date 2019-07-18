package com.example.domain.interaction.base

abstract class BaseParamUseCase<T, R> {
  
  protected abstract fun execute(param: T): R
}