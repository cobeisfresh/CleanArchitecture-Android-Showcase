package com.cobeisfresh

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.common.coroutine.TestCoroutineContextProvider
import com.cobeisfresh.template.ui.base.ViewState
import com.cobeisfresh.template.ui.weather.presentation.WeatherViewModel
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.Success
import com.example.domain.model.WeatherInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class WeatherViewModelTest {
  
  private val getWeather: GetWeatherUseCase = mock()
  private val coroutineContext = TestCoroutineContextProvider()
  private val weatherViewModel by lazy { WeatherViewModel(getWeather, coroutineContext) }
  
  @get:Rule
  val rule: TestRule = InstantTaskExecutorRule()
  @get:Rule
  val mockitoRule: MockitoRule = MockitoJUnit.rule()
  
  @Test
  fun `test getWeather sets liveData value when success`() = runBlocking {
    whenever(getWeather(OSIJEK_CITY_NAME)).thenReturn(Success(WeatherInfo(TEMP, HUMIDITY, PRESSURE)))
    weatherViewModel.getWeatherForLocation(OSIJEK_CITY_NAME)
    assertEquals(ViewState.Status.SUCCESS, weatherViewModel.weatherLiveData.value?.status)
  }
}