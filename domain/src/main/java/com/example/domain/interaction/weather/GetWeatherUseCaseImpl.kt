package com.example.domain.interaction.weather

import com.example.domain.repository.WeatherRepository

class GetWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetWeatherUseCase {
  
  override suspend operator fun invoke(location: String) = weatherRepository.getWeatherForLocation(location)
}