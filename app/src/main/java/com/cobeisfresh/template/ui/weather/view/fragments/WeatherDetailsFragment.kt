package com.cobeisfresh.template.ui.weather.view.fragments

import com.cobeisfresh.template.R
import com.cobeisfresh.template.ui.base.BaseFragment

class WeatherDetailsFragment: BaseFragment() {
  
  override fun viewReady() {}
  
  override fun getLayout() = R.layout.fragment_weather_details
  
  companion object{
     fun newInstance() = WeatherDetailsFragment()
  }
}