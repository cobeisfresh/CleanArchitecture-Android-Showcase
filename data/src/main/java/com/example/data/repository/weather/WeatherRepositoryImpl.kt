package com.example.data.repository.weather

import com.example.data.networking.GENERAL_NETWORK_ERROR
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.onFailure
import com.example.data.networking.base.onSuccess
import com.example.data.networking.model.mapToWeatherInfo
import com.example.domain.model.Failure
import com.example.domain.model.Result
import com.example.domain.model.Success
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    weatherApi.getWeatherForLocation(location)
        .onSuccess { return Success(it.mapToWeatherInfo()) }
        .onFailure { return Failure(it) }
    return Failure(GENERAL_NETWORK_ERROR)
  }
}