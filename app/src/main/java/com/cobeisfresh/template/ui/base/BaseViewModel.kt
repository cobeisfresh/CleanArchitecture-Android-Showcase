package com.cobeisfresh.template.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cobeisfresh.template.common.extensions.launch
import com.example.data.common.coroutine.CoroutineContextProvider
import com.example.data.common.utils.Connectivity
import com.example.domain.base.BaseUseCase
import com.example.domain.model.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {
  
  protected val coroutineContext: CoroutineContextProvider by inject()
  private val connectivity: Connectivity by inject()
  
  protected val _viewState = MutableLiveData<ViewState<T>>()
  val viewState: LiveData<ViewState<T>>
    get() = _viewState
  
  protected val _viewEffects = MutableLiveData<E>()
  val viewEffects: LiveData<E>
    get() = _viewEffects
  
  protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
    _viewState.value = Loading()
    if (connectivity.hasNetworkAccess()) {
      launch { action() }
    } else {
      noInternetAction()
    }
  }
  
  protected fun executeUseCase(action: suspend () -> Unit) {
    _viewState.value = Loading()
    launch { action() }
  }
}