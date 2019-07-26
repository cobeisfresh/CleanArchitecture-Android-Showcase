package com.cobeisfresh.template.di

import androidx.appcompat.app.AppCompatActivity
import com.cobeisfresh.template.routing.AppNavigator
import org.koin.dsl.module

val appModule = module {
  single { (activity: AppCompatActivity) -> AppNavigator(activity) }
}