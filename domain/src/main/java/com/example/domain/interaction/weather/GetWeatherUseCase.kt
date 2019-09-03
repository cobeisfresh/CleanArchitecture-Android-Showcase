package com.example.domain.interaction.weather

import com.example.domain.base.BaseUseCase
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfo

interface GetWeatherUseCase : BaseUseCase<String, WeatherInfo> {
  
  override suspend operator fun invoke(location: String): Result<WeatherInfo>
}