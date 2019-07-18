package com.example.domain.interaction.base

abstract class BaseUseCase<T> {
  
  protected abstract fun execute(): T
}