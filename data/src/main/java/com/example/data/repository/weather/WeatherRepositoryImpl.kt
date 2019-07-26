package com.example.data.repository.weather

import com.example.data.database.dao.WeatherDao
import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.onFailure
import com.example.data.networking.base.onSuccess
import com.example.domain.model.*
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.delay

class WeatherRepositoryImpl(private val weatherApi: WeatherApi, private val weatherDao: WeatherDao) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    weatherApi.getWeatherForLocation(location)
        .onSuccess {
          val weatherInfo = weatherDao.updateWeatherAndReturn(it.mapToRoomEntity())
          return Success(weatherInfo.mapToDomainModel())
        }
        .onFailure { return Failure(it) }
    return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
  }
}