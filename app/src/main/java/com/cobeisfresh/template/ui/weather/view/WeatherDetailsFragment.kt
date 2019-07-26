package com.cobeisfresh.template.ui.weather.view

import com.cobeisfresh.template.R
import com.cobeisfresh.template.ui.weather.base.BaseFragment

class WeatherDetailsFragment: BaseFragment() {
  
  override fun viewReady() {
  
  }
  
  override fun getLayout() = R.layout.fragment_weather_details
  
  companion object{
     fun newInstance() = WeatherDetailsFragment()
  }
}