package com.example.data.database.dao

import androidx.room.*
import com.example.data.database.model.WeatherEntity

@Dao
interface WeatherDao {
  
  @Transaction
  suspend fun updateWeatherAndReturn(weather: WeatherEntity): WeatherEntity {
    saveWeatherInfo(weather)
    return getWeatherInfo()
  }
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveWeatherInfo(weather: WeatherEntity)
  
  @Query("SELECT * FROM weather")
  suspend fun getWeatherInfo(): WeatherEntity
}