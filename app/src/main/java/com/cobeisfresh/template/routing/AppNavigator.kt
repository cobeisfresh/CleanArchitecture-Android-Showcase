package com.cobeisfresh.template.routing

import android.content.Intent
import com.cobeisfresh.template.ui.weather.base.BaseActivity
import com.cobeisfresh.template.ui.weather.view.WeatherActivity

class AppNavigator(private val activity: BaseActivity) : Navigator {
  
  override fun showWeather() = navigateTo(getIntent<WeatherActivity>())
  
  private fun navigateTo(intent: Intent) = activity.startActivity(intent)
  
  private inline fun <reified T : BaseActivity> getIntent() = Intent(activity, T::class.java)
}