package com.example.domain

import com.example.domain.interaction.weather.GetWeatherUseCaseImpl
import com.example.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyBlocking
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnitRunner

class WeatherUseCaseTest {
  
  private val weatherRepository: WeatherRepository = mock()
  private val getWeather by lazy { GetWeatherUseCaseImpl(weatherRepository) }
  
  @Test
  fun `test GetWeatherUseCase calls WeatherRepository`() {
    runBlocking {
      getWeather(OSIJEK_CITY_NAME)
      verify(weatherRepository).getWeatherForLocation(OSIJEK_CITY_NAME)
    }
  }
}