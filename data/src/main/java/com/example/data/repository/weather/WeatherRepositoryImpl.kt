package com.example.data.repository.weather

import com.example.data.networking.WeatherApi
import com.example.data.networking.model.mapToWeatherInfo
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
  
  //TODO import Retrofit
  override fun getWeatherForLocation(location: String): WeatherInfo {
    return weatherApi.getWeatherForLocation(location).mapToWeatherInfo()
  }
}