package com.example.data.di

import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
  //TODO scope this to weather viewmodel
  single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}