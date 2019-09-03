package com.example.data

import com.example.data.common.utils.Connectivity
import com.example.data.database.dao.WeatherDao
import com.example.data.networking.WeatherApi
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.data.utils.*
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class WeatherRepositoryTest {
  
  private val weatherTestApi: WeatherApi = mock()
  private val weatherDao: WeatherDao = mock()
  private val connectivity: Connectivity = mock()
  private val weatherRepository = WeatherRepositoryImpl(weatherTestApi, weatherDao, connectivity)
  
  @Test
  fun `test getWeatherForLocation calls api and saves data to db upon success`() {
    runBlocking {
      whenever(connectivity.hasNetworkAccess()).thenReturn(true)
      whenever(weatherTestApi.getWeatherForLocation(OSIJEK_CITY_NAME)).thenReturn(Response.success(successWeatherInfoResponse))
      whenever(weatherDao.updateWeatherAndReturn(successWeatherInfoResponse.mapToRoomEntity())).thenReturn(fakeWeatherEntity)
      weatherRepository.getWeatherForLocation(OSIJEK_CITY_NAME)
      
      verify(weatherTestApi, times(1)).getWeatherForLocation(OSIJEK_CITY_NAME)
      verify(weatherDao, times(1)).updateWeatherAndReturn(fakeWeatherEntity)
    }
  }
  
  @Test
  fun `test getWeatherForLocation calls api and returns cached data from db upon failure`() {
    runBlocking {
      whenever(connectivity.hasNetworkAccess()).thenReturn(true)
      whenever(weatherTestApi.getWeatherForLocation(OSIJEK_CITY_NAME))
          .thenReturn(Response.error(FAKE_FAILURE_ERROR_CODE, failureResponseBody))
      weatherRepository.getWeatherForLocation(OSIJEK_CITY_NAME)
      
      verify(weatherTestApi, times(1)).getWeatherForLocation(OSIJEK_CITY_NAME)
      verify(weatherDao, never()).updateWeatherAndReturn(fakeWeatherEntity)
    }
  }
}