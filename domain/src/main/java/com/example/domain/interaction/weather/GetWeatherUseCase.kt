package com.example.domain.interaction.weather

import com.example.domain.interaction.base.BaseParamUseCase
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) : BaseParamUseCase<String, WeatherInfo>() {
  override fun execute(param: String) = weatherRepository.getWeatherForLocation(param)
}