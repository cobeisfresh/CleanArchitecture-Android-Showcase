package com.example.data.networking

import com.example.data.networking.model.WeatherInfoResponse

interface WeatherApi {
  
  //TODO import Retrofit
  fun getWeatherForLocation(location: String): WeatherInfoResponse
}