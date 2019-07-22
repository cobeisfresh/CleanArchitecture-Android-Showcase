package com.example.domain.interaction.weather

import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class GetWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetWeatherUseCase {
  
  override suspend fun execute(location: String): Result<WeatherInfo> {
    return weatherRepository.getWeatherForLocation(location)
  }
}