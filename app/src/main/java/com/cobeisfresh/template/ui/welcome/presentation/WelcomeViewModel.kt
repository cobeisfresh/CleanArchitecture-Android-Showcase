package com.cobeisfresh.template.ui.welcome.presentation

import com.cobeisfresh.template.routing.AppNavigator
import com.cobeisfresh.template.ui.base.NavigatorViewModel

class WelcomeViewModel : NavigatorViewModel() {
  
  fun proceedToNextScreen() {
    appNavigator.showWeather()
  }
  
  override fun initAppNavigator(appNavigator: AppNavigator) {
    this.appNavigator = appNavigator
  }
}
