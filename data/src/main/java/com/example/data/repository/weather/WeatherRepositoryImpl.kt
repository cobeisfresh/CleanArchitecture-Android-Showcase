package com.example.data.repository.weather

import com.example.data.database.dao.WeatherDao
import com.example.data.database.model.WeatherEntity
import com.example.data.networking.WeatherApi
import com.example.data.networking.base.getData
import com.example.domain.model.Result
import com.example.domain.model.WeatherInfo
import com.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi,
                            private val weatherDao: WeatherDao) : BaseRepository<WeatherInfo, WeatherEntity>(), WeatherRepository {
  override suspend fun getWeatherForLocation(location: String): Result<WeatherInfo> {
    return fetchData(
      apiDataProvider = {
        weatherApi.getWeatherForLocation(location).getData(
          fetchFromCacheAction = { weatherDao.getWeatherInfoForCity(location) },
          cacheAction = { weatherDao.saveWeatherInfo(it) })
      },
      dbDataProvider = { weatherDao.getWeatherInfoForCity(location) }
    )
  }
}