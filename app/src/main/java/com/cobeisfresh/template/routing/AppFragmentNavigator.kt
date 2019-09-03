package com.cobeisfresh.template.routing

import androidx.fragment.app.FragmentActivity
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.showFragment
import com.cobeisfresh.template.ui.weather.view.fragments.WeatherDetailsFragment

/**
 * Every activity that holds fragments should name its container "fragmentContainer"
 */

class AppFragmentNavigator(private val activity: FragmentActivity) : FragmentNavigator {
  
  override fun showWeatherDetails() = activity.showFragment(WeatherDetailsFragment.newInstance(), R.id.fragmentContainer, true)
}

