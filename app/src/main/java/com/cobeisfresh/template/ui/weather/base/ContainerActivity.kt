package com.cobeisfresh.template.ui.weather.base

import android.os.Bundle
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.showFragment
import com.cobeisfresh.template.routing.AppNavigator
import com.cobeisfresh.template.routing.ScreenType
import com.cobeisfresh.template.ui.weather.view.WeatherDetailsFragment

/**
 * Activity that shows fragment inside container. ScreenType is sent through bundle
 */
class ContainerActivity : BaseActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)
    when (intent.getSerializableExtra(AppNavigator.SCREEN_TYPE)) {
      ScreenType.WEATHER_DETAILS -> addFragment(WeatherDetailsFragment.newInstance(), R.id.fragmentContainer)
      else -> finish()
    }
  }
}
