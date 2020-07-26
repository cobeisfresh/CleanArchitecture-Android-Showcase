package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.WEATHER_TABLE_NAME
import com.example.data.database.model.WeatherEntity

@Dao
interface WeatherDao {
  
  @Transaction
  suspend fun updateWeatherAndReturn(weather: WeatherEntity): WeatherEntity {
    saveWeatherInfo(weather)
    return getWeatherInfoForCity(weather.name ?: "")
  }
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveWeatherInfo(weather: WeatherEntity)
  
  @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE name = :city LIMIT 1")
  suspend fun getWeatherInfoForCity(city: String): WeatherEntity

  /**
   *NOTICE: This is a dummy method used as an example for handling Lists of data
   */
  @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE name = :city")
  suspend fun getWeatherInfoList(city: String): List<WeatherEntity>

  /**
   *NOTICE: This is a dummy method used as an example for handling Lists of data
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveWeatherInfo(weatherList: List<WeatherEntity>)
}