package com.cobeisfresh

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cobeisfresh.template.ui.weather.base.ViewState
import com.cobeisfresh.template.ui.weather.presentation.WeatherViewModel
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.Success
import com.example.domain.model.WeatherInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
  
  private val getWeather: GetWeatherUseCase = mock()
  private val weatherViewModel by lazy { WeatherViewModel(getWeather) }
  private val fakeMainThread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
  private val fakeObserver: Observer<ViewState<WeatherInfo>> = mock()
  
  @get:Rule
  val rule: TestRule = InstantTaskExecutorRule()
  
  @Before
  fun setUp() {
    Dispatchers.setMain(fakeMainThread)
  }
  
  @Test
  fun `test getWeather sets liveData value when success`() = runBlocking {
    weatherViewModel.weatherLiveData.observeForever(fakeObserver)
    whenever(getWeather(OSIJEK_CITY_NAME)).thenReturn(Success(WeatherInfo(TEMP, HUMIDITY, PRESSURE)))
    weatherViewModel.getWeatherForLocation(OSIJEK_CITY_NAME)
    assertEquals(ViewState.Status.LOADING, weatherViewModel.weatherLiveData.value?.status)
  }
  
  @After
  fun tearDown() {
    Dispatchers.resetMain()
    fakeMainThread.close()
    weatherViewModel.weatherLiveData.removeObserver(fakeObserver)
  }
}