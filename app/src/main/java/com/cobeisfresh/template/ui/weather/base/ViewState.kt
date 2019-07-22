package com.cobeisfresh.template.ui.weather.base

import com.cobeisfresh.template.ui.weather.base.ViewState.Status.*

data class ViewState<T>(val status: Status, val data: T?, val error: Throwable?) {
  
  enum class Status {
    SUCCESS, ERROR, LOADING
  }
  
  companion object {
    fun <T> success(data: T): ViewState<T> {
      return ViewState(SUCCESS, data, null)
    }
    
    fun <T> error(error: Throwable): ViewState<T> {
      return ViewState(ERROR, null, error)
    }
    
    fun <T> loading(): ViewState<T> {
      return ViewState(LOADING, null, null)
    }
  }
}

