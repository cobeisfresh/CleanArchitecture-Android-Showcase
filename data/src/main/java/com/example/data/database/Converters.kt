package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.networking.model.MainInfo
import com.example.data.networking.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
  private val gson = Gson()
  
  // Weather list converters
  
  @TypeConverter
  fun fromWeatherListToJson(list: List<Weather>?): String {
    return list?.let { gson.toJson(it) } ?: ""
  }
  
  @TypeConverter
  fun fromJsonToWeatherList(jsonList: String): List<Weather> {
    val listType = object : TypeToken<List<Weather>>() {}.type
    return gson.fromJson(jsonList, listType)
  }
  
  // MainInfo converters
  
  @TypeConverter
  fun fromMainInfoToJson(mainInfo: MainInfo?): String {
    return mainInfo?.let { gson.toJson(it) } ?: ""
  }
  
  @TypeConverter
  fun fromJsonToMainInfo(json: String): MainInfo {
    val type = object : TypeToken<MainInfo>() {}.type
    return gson.fromJson(json, type)
  }
}