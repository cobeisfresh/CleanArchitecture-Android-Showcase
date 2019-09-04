package com.example.data.repository.weather

import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import com.example.data.database.DB_ENTRY_ERROR
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.networking.base.DomainMapper
import com.example.domain.model.Failure
import com.example.domain.model.HttpError
import com.example.domain.model.Result
import com.example.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
  private val connectivity: Connectivity by inject()
  private val contextProvider: CoroutineContextProvider by inject()
  
  /**
   * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
   */
  protected suspend fun fetchData(
      apiDataProvider: suspend () -> Result<T>,
      dbDataProvider: suspend () -> R
  ): Result<T> {
    return if (connectivity.hasNetworkAccess()) {
      withContext(contextProvider.io) {
        apiDataProvider()
      }
    } else {
      withContext(contextProvider.io) {
        val dbResult = dbDataProvider()
        if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
      }
    }
  }
  
  /**
   * Use this when communicating only with the api service
   */
  protected suspend fun fetchData(dataProvider: () -> Result<T>): Result<T> {
    return if (connectivity.hasNetworkAccess()) {
      withContext(contextProvider.io) {
        dataProvider()
      }
    } else {
      Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
  }
}