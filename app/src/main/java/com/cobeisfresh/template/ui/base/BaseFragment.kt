package com.cobeisfresh.template.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.cobeisfresh.template.routing.AppFragmentNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {
  
  protected val appFragmentNavigator: AppFragmentNavigator by inject { parametersOf(activity) }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(getLayout(), container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewReady()
  }
  
  protected fun onBackPressed() = (activity as BaseActivity).onBackPressed()
  
  abstract fun viewReady()
  
  abstract fun getLayout(): Int
  
  open fun showError(@StringRes errorMessage: Int, rootView: View) {
    (activity as BaseActivity).showError(errorMessage, rootView)
  }
  
  open fun showError(errorMessage: String?, rootView: View) {
    (activity as BaseActivity).showError(errorMessage, rootView)
  }
  
  open fun showLoading(progressBar: ProgressBar) {
    (activity as BaseActivity).showLoading(progressBar)
  }
  
  open fun hideLoading(progressBar: ProgressBar) {
    (activity as BaseActivity).hideLoading(progressBar)
  }
}