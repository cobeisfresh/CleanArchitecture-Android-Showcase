package com.example.data.repository.weather

import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.onFailure
import com.example.data.networking.base.onSuccess
import com.example.domain.model.*
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    weatherApi.getWeatherForLocation(location)
        .onSuccess { return Success(it.mapToDomainModel()) }
        .onFailure { return Failure(it) }
    return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
  }
}