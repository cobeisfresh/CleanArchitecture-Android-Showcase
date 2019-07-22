package com.example.domain.interaction.weather

import com.example.domain.model.Result
import com.example.domain.model.WeatherInfo

interface GetWeatherUseCase {
  
  suspend fun execute(location: String): Result<WeatherInfo>
}