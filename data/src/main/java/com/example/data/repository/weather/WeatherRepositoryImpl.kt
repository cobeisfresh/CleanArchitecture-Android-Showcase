package com.example.data.repository.weather

import com.example.data.database.dao.WeatherDao
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.getData
import com.example.data.utils.Connectivity
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi,
                            private val weatherDao: WeatherDao,
                            private val connectivity: Connectivity) : WeatherRepository {
  
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
//    return weatherApi.getWeatherForLocation(location).getData(connectivity)
    return weatherApi.getWeatherForLocation(location).getData(connectivity,
                                                              fetchFromCacheAction = { weatherDao.getWeatherInfoForCity(location) },
                                                              cacheAction = { weatherDao.updateWeatherAndReturn(it) })
  }
}