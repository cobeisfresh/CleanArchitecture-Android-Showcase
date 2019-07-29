package com.cobeisfresh.template.ui.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cobeisfresh.template.common.DEFAULT_CITY_NAME
import com.cobeisfresh.template.common.coroutine.CoroutineContextProvider
import com.cobeisfresh.template.ui.weather.base.ViewState
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.WeatherInfo
import com.example.domain.model.onFailure
import com.example.domain.model.onSuccess
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(private val getWeather: GetWeatherUseCase,
                       private val coroutineContextProvider: CoroutineContextProvider) : ViewModel(), KoinComponent {
  
  // we make this private and provide only immutable live data to observers so they can't change anything
  private val _weatherLiveData = MutableLiveData<ViewState<WeatherInfo>>()
  val weatherLiveData: LiveData<ViewState<WeatherInfo>>
    get() = _weatherLiveData
  
  fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) =
      viewModelScope.launch(coroutineContextProvider.main) {
        _weatherLiveData.value = ViewState.loading()
        getWeather(location)
            .onSuccess { _weatherLiveData.value = ViewState.success(it) }
            .onFailure { _weatherLiveData.value = ViewState.error(it.throwable) }
      }
}
