package com.example.data.repository.weather

import com.example.data.database.DB_ENTRY_ERROR
import com.example.data.database.dao.WeatherDao
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.onFailure
import com.example.data.networking.base.onSuccess
import com.example.data.utils.Connectivity
import com.example.domain.model.*
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi,
                            private val weatherDao: WeatherDao,
                            private val connectivity: Connectivity) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    if (connectivity.hasNetworkAccess()) {
      weatherApi.getWeatherForLocation(location)
          .onSuccess {
            val weatherInfo = weatherDao.updateWeatherAndReturn(it.mapToRoomEntity())
            return Success(weatherInfo.mapToDomainModel())
          }
          .onFailure { return Failure(it) }
      return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } else {
      val weatherInfo = weatherDao.getWeatherInfoForCity(location)
      @Suppress("SENSELESS_COMPARISON") // room can return null here even though it's not nullable type
      return if (weatherInfo != null) Success(weatherInfo.mapToDomainModel())
      else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
    }
  }
}