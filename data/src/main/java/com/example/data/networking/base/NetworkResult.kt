package com.example.data.networking.base

import com.example.data.database.DB_ENTRY_ERROR
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.utils.Connectivity
import com.example.domain.model.Failure
import com.example.domain.model.HttpError
import com.example.domain.model.Result
import com.example.domain.model.Success
import retrofit2.Response
import java.io.IOException

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

//TODO add withContext
suspend inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    connectivity: Connectivity,
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R?): Result<U> {
  try {
    if (connectivity.hasNetworkAccess()) {
      onSuccess {
        val databaseEntity = it.mapToRoomEntity()
        cacheAction(databaseEntity)
        
        return Success(databaseEntity.mapToDomainModel())
      }
      onFailure { return Failure(it) }
      return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } else {
      val cachedModel = fetchFromCacheAction()
      return if (cachedModel != null) Success(cachedModel.mapToDomainModel())
      else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
    }
  } catch (e: IOException) {
    return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
  }
}

suspend fun <T : DomainMapper<R>, R : Any> Response<T>.getData(connectivity: Connectivity): Result<R> {
  try {
    if (connectivity.hasNetworkAccess()) {
      onSuccess { return Success(it.mapToDomainModel()) }
      onFailure { return Failure(it) }
      return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } else {
      return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
  } catch (e: IOException) {
    return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
  }
}
