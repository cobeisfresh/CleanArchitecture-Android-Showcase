package com.cobeisfresh.template

import android.app.Application
import com.cobeisfresh.template.di.presentationModule
import com.example.data.di.networkingModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactionModule
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
      modules(appModules + domainModules + dataModules)
    }
  }
}

val appModules = listOf(presentationModule)
val domainModules = listOf(interactionModule)
val dataModules = listOf(networkingModule, repositoryModule)
