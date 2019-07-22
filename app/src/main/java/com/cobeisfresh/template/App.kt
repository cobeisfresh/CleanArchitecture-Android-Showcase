package com.cobeisfresh.template

import android.app.Application
import com.example.data.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
  
  lateinit var INSTANCE: Application
    private set
  
  override fun onCreate() {
    super.onCreate()
    INSTANCE = this
    
    startKoin {
      androidContext(this@App)
      modules(domainModules)
    }
  }
}

val domainModules = listOf(networkingModule)