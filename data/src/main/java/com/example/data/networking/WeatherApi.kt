package com.example.data.networking

import com.example.data.di.API_KEY
import com.example.data.networking.model.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
  
  @GET("weather")
  suspend fun getWeatherForLocation(@Query("q") location: String, @Query("appid") apiKey: String = API_KEY): Response<WeatherInfoResponse>
}