package com.example.data.networking.base

import com.example.domain.model.HttpError
import com.example.domain.model.Result
import retrofit2.Response

abstract class NetworkResult<out T : Any> : Mapper<T>

interface Mapper<out T : Any> {
  
  fun mapToDomainModel(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
  if (isSuccessful) body()?.run(action)
  return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
  if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}