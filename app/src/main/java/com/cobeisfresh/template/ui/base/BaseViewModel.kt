package com.cobeisfresh.template.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.common.coroutine.CoroutineContextProvider
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
  
  protected val coroutineContext: CoroutineContextProvider by inject()
  
  protected val _viewState = MutableLiveData<ViewState<T>>()
  val viewState: LiveData<ViewState<T>>
    get() = _viewState
  
  protected val _viewEffects = MutableLiveData<E>()
  val viewEffects: LiveData<E>
    get() = _viewEffects
}