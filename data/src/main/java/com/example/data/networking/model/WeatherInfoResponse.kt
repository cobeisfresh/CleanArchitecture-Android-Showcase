package com.example.data.networking.model

import com.example.domain.model.WeatherInfo

data class WeatherInfoResponse(val temperature: Float? = 0.0F, val humidity: Float? = 0.0F, val pressure: Float? = 0.0F)

fun WeatherInfoResponse.mapToWeatherInfo(): WeatherInfo {
  return if (temperature != null && humidity != null && pressure != null) {
    WeatherInfo(temperature, humidity, pressure)
  } else {
    WeatherInfo(0F, 0F, 0F)
  }
}
