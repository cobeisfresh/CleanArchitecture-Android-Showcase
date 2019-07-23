package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.networking.model.MainInfo
import com.example.data.networking.model.Weather
import com.google.gson.Gson
import java.util.ArrayList
import com.google.gson.reflect.TypeToken

class Converters {
  private val gson = Gson()
  
  // Weather list converters
  
  @TypeConverter
  fun fromListToJson(list: ArrayList<Weather>?): String {
    return list?.let { gson.toJson(it) } ?: ""
  }
  
  @TypeConverter
  fun fromJsonToList(jsonList: String): ArrayList<Weather> {
    val listType = object : TypeToken<ArrayList<Weather>>() {}.type
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