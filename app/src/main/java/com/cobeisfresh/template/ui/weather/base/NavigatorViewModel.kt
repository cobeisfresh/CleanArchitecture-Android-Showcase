package com.cobeisfresh.template.ui.weather.base

import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.routing.AppNavigator

/**
 * Every view model that needs to redirect user to another screen should extend NavigatorViewModel and implement initAppNavigator
 * method which takes in appNavigator instance sent from Activity connected with that ViewModel.
 */
abstract class NavigatorViewModel : ViewModel() {
  
  protected lateinit var appNavigator: AppNavigator
  
  abstract fun initAppNavigator(appNavigator: AppNavigator)
}