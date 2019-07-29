package com.cobeisfresh.template.ui.welcome.view

import android.os.Bundle
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.onClick
import com.cobeisfresh.template.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)
    
    nextScreen.onClick {
      appNavigator.showWeatherActivity()
      finish()
    }
  }
}