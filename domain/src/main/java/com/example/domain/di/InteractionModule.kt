package com.example.domain.di

import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.interaction.weather.GetWeatherUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
  factory<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
}
