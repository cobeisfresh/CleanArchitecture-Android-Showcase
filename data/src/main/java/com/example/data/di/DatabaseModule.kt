package com.example.data.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val WEATHER_DB = "weather-database"

val databaseModule = module {
  single {
    //TODO remove fallbackToDestructiveMigration when this goes to production
    Room.databaseBuilder(androidContext(), WeatherDatabase::class.java, WEATHER_DB).fallbackToDestructiveMigration().build()
  }
  factory { get<WeatherDatabase>().weatherDao() }
}