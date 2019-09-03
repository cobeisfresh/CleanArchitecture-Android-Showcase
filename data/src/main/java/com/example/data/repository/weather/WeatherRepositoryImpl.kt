package com.example.data.repository.weather

import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import com.example.data.database.dao.WeatherDao
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.getData
import com.example.domain.model.Result
import com.example.domain.model.Success
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val weatherApi: WeatherApi,
                            private val weatherDao: WeatherDao,
                            private val connectivity: Connectivity,
                            private val contextProvider: CoroutineContextProvider) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    return if (connectivity.hasNetworkAccess()) {
      withContext(contextProvider.io) {
        weatherApi.getWeatherForLocation(location).getData(fetchFromCacheAction = { weatherDao.getWeatherInfoForCity(location) },
                                                           cacheAction = { weatherDao.updateWeatherAndReturn(it) })
      }
    } else {
      withContext(contextProvider.io) {
        val weather = weatherDao.getWeatherInfoForCity(location)
        Success(weather.mapToDomainModel())
      }
    }
  }
}