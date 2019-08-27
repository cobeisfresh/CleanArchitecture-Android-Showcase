package com.example.data.networking.base

import com.example.domain.model.HttpError
import retrofit2.Response

interface DomainMapper<out T : Any> {
  fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
  fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
  if (isSuccessful) body()?.run(action)
  return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
  if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}