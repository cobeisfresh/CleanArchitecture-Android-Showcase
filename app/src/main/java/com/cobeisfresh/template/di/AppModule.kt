package com.cobeisfresh.template.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.cobeisfresh.template.routing.AppFragmentNavigator
import com.cobeisfresh.template.routing.AppNavigator
import com.example.data.common.coroutine.CoroutineContextProvider
import org.koin.dsl.module

val appModule = module {
  single { CoroutineContextProvider() }
  single { (activity: AppCompatActivity) -> AppNavigator(activity) }
  single { (activity: FragmentActivity) -> AppFragmentNavigator(activity) }
}