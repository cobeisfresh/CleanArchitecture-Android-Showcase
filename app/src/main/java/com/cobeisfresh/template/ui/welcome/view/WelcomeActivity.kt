package com.cobeisfresh.template.ui.welcome.view

import android.os.Bundle
import com.cobeisfresh.template.R
import com.cobeisfresh.template.common.extensions.onClick
import com.cobeisfresh.template.ui.base.BaseActivity
import com.cobeisfresh.template.ui.welcome.presentation.WelcomeViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import org.koin.android.viewmodel.ext.android.viewModel

class WelcomeActivity : BaseActivity() {
  
  private val viewModel by viewModel<WelcomeViewModel>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)
    viewModel.initAppNavigator(appNavigator)
    nextScreen.onClick {
      viewModel.proceedToNextScreen()
      finish()
    }
  }
}