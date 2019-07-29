package com.cobeisfresh.template.ui.weather.base

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.cobeisfresh.template.common.EMPTY_STRING
import com.cobeisfresh.template.common.extensions.gone
import com.cobeisfresh.template.common.extensions.snackbar
import com.cobeisfresh.template.common.extensions.visible
import com.cobeisfresh.template.routing.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : AppCompatActivity() {
  
  protected val appNavigator: AppNavigator by inject { parametersOf(this) }
  
  fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)
  
  fun showError(errorMessage: String?, rootView: View) = snackbar(errorMessage ?: EMPTY_STRING, rootView)
  
  fun showLoading(progressBar: ProgressBar) = progressBar.visible()
  
  fun hideLoading(progressBar: ProgressBar) = progressBar.gone()
}