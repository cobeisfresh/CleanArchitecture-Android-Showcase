package com.cobeisfresh.template.ui.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.common.DEFAULT_CITY_NAME
import com.cobeisfresh.template.common.extensions.launch
import com.cobeisfresh.template.ui.base.*
import com.cobeisfresh.template.ui.weather.view.WeatherViewEffects
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.WeatherInfo
import com.example.domain.model.onFailure
import com.example.domain.model.onSuccess

class WeatherViewModel(private val getWeather: GetWeatherUseCase) : BaseViewModel<WeatherInfo, WeatherViewEffects>() {
  
  // we make this private and provide only immutable live data to observers so they can't change anything
  private val _weatherLiveData = MutableLiveData<ViewState<WeatherInfo>>()
  val weatherLiveData: LiveData<ViewState<WeatherInfo>>
    get() = _weatherLiveData
  
  fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) =
      launch {
        _viewState.value = Loading()
        getWeather(location)
            .onSuccess { _viewState.value = Success(it) }
            .onFailure { _viewState.value = Error(it.throwable) }
      }
}
