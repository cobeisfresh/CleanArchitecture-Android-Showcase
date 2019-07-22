package com.example.data.networking.base

import retrofit2.Response

//TODO check case when coroutine is canceled
inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
  if (isSuccessful) body()?.run(action)
  return this
}

//TODO replace String with HttpError
inline fun <T : Any> Response<T>.onFailure(action: (String) -> Unit) {
  if (!isSuccessful) errorBody()?.run { action(message()) }
}