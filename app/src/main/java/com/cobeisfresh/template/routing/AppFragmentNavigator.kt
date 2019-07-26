package com.cobeisfresh.template.routing

import androidx.fragment.app.FragmentActivity
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.showFragment
import com.cobeisfresh.template.ui.weather.view.fragments.WeatherDetailsFragment

class AppFragmentNavigator : FragmentNavigator {
  
  override fun showWeatherDetails(fragmentActivity: FragmentActivity) =
      fragmentActivity.showFragment(WeatherDetailsFragment.newInstance(), R.id.fragmentContainer, true)
  
}

