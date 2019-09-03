package com.example.domain.repository

import com.example.domain.model.Result
import com.example.domain.model.WeatherInfo

interface WeatherRepository {
  suspend fun getWeatherForLocation(location: String): Result<WeatherInfo>
}