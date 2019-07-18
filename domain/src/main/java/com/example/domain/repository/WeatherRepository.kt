package com.example.domain.repository

import com.example.domain.model.WeatherInfo

interface WeatherRepository {
  
  fun getWeatherForLocation(location: String): WeatherInfo
}