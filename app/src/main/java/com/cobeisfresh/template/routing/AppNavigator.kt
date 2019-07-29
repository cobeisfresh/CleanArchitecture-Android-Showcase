package com.cobeisfresh.template.routing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.cobeisfresh.template.ui.base.BaseActivity
import com.cobeisfresh.template.ui.weather.view.activities.WeatherActivity
import java.io.Serializable

class AppNavigator(private val activity: AppCompatActivity) : Navigator {
  
  companion object {
    const val SCREEN_TYPE = "screen_type"
  }
  
  override fun showWeatherActivity() = navigateTo(getIntent<WeatherActivity>().apply {
    putExtra(SCREEN_TYPE, ScreenType.WEATHER)
  })
  
  private fun navigateTo(intent: Intent) = activity.startActivity(intent)
  
  private inline fun <reified T : BaseActivity> getIntent() = Intent(activity, T::class.java)
}

enum class ScreenType : Serializable {
  WEATHER
}
