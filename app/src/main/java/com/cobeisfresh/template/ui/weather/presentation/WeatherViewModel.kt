package com.cobeisfresh.template.ui.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.common.DEFAULT_CITY_NAME
import com.cobeisfresh.template.common.extensions.launch
import com.cobeisfresh.template.ui.base.Error
import com.cobeisfresh.template.ui.base.Loading
import com.cobeisfresh.template.ui.base.Success
import com.cobeisfresh.template.ui.base.ViewState
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.WeatherInfo
import com.example.domain.model.onFailure
import com.example.domain.model.onSuccess

class WeatherViewModel(private val getWeather: GetWeatherUseCase) : ViewModel() {
  
  // we make this private and provide only immutable live data to observers so they can't change anything
  private val _weatherLiveData = MutableLiveData<ViewState<WeatherInfo>>()
  val weatherLiveData: LiveData<ViewState<WeatherInfo>>
    get() = _weatherLiveData
  
  fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) =
      launch {
        _weatherLiveData.value = Loading()
        getWeather(location)
            .onSuccess { _weatherLiveData.postValue(Success(it)) }
            .onFailure { _weatherLiveData.postValue(Error(it.throwable)) }
      }
}
