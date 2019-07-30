package com.cobeisfresh.template.ui.weather.view.fragments

import com.cobeisfresh.template.R
import com.cobeisfresh.template.ui.base.BaseFragment

class WeatherDetailsFragment : BaseFragment() {
  
  override fun getLayout() = R.layout.fragment_weather_details
  
  override fun viewReady() {}
  
  companion object {
    fun newInstance() = WeatherDetailsFragment()
  }
}