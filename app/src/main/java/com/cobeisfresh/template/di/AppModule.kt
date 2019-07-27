package com.cobeisfresh.template.di

import com.cobeisfresh.template.common.coroutine.CoroutineContextProvider
import org.koin.dsl.module

val appModule = module {
  single { CoroutineContextProvider() }
}