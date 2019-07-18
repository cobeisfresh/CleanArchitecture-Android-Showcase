package com.cobeisfresh.template.ui.weather.base

import com.cobeisfresh.template.ui.weather.base.ViewState.Status.*

data class ViewState<T>(val status: Status, val data: T?, val error: Throwable?) {
  
  enum class Status {
    SUCCESS, ERROR, LOADING
  }
  
  companion object {
    fun <T> success(data: T) = ViewState(SUCCESS, data, null)
    fun error(error: Throwable) = ViewState(ERROR, null, error)
    fun loading() = ViewState(LOADING, null, null)
  }
}