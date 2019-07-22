package com.cobeisfresh.template.ui.weather.view

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.convertKelvinToCelsius
import com.cobeisfresh.template.common.extensions.onClick
import com.cobeisfresh.template.common.extensions.subscribe
import com.cobeisfresh.template.ui.weather.base.BaseActivity
import com.cobeisfresh.template.ui.weather.base.ViewState
import com.cobeisfresh.template.ui.weather.base.ViewState.Status.*
import com.cobeisfresh.template.ui.weather.presentation.WeatherViewModel
import com.example.domain.model.WeatherInfo
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {
  
  private val viewModel: WeatherViewModel by viewModel()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_weather)
    
    viewModel.getWeatherForLocation()
    subscribeToData()
    getWeather.onClick {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(weatherActivityContainer.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
      viewModel.getWeatherForLocation(cityInput.text.toString())
    }
  }
  
  private fun subscribeToData() = with(viewModel) {
    weatherLiveData.subscribe(this@WeatherActivity, ::handleViewState)
  }
  
  private fun handleViewState(viewState: ViewState<WeatherInfo>) {
    with(viewState) {
      when (status) {
        LOADING -> showLoading(weatherLoadingProgress)
        SUCCESS -> data?.run(::showWeatherData)
        ERROR -> {
          hideLoading(weatherLoadingProgress)
          showError(error?.message, weatherActivityContainer)
        }
      }
    }
  }
  
  private fun showWeatherData(weatherInfo: WeatherInfo) {
    hideLoading(weatherLoadingProgress)
    temperature.text = convertKelvinToCelsius(weatherInfo.temperature)
    pressure.text = weatherInfo.pressure.toString()
    humidity.text = weatherInfo.humidity.toString()
  }
}
