package com.example.data.di

import com.example.data.common.utils.Connectivity
import com.example.data.common.utils.ConnectivityImpl
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.domain.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
  factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
  factory<Connectivity> { ConnectivityImpl(androidContext()) }
}