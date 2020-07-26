package com.example.data.networking

import com.example.data.di.API_KEY
import com.example.data.networking.model.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherForLocation(
        @Query("q") location: String,
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherInfoResponse>

    /**
     *NOTICE: This is a dummy method just to showcase how you can handle response when it comes as a List<T>
     */
    @GET("no-op")
    suspend fun getWeatherForLocations(locations: List<String>): Response<List<WeatherInfoResponse>>
}