package com.cobeisfresh.template.ui.weather.view.activities

import android.os.Bundle
import com.cobeisfresh.template.R
import com.cobeisfresh.template.routing.AppNavigator
import com.cobeisfresh.template.routing.ScreenType
import com.cobeisfresh.template.ui.base.BaseActivity
import com.cobeisfresh.template.ui.weather.view.fragments.WeatherFragment

class WeatherActivity : BaseActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
    when (intent.getSerializableExtra(AppNavigator.SCREEN_TYPE)) {
      ScreenType.WEATHER -> addFragment(WeatherFragment.newInstance(), R.id.fragmentContainer, true)
      else -> finish()
    }
  }
}
