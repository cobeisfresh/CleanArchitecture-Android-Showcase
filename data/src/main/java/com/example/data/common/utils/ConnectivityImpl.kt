package com.example.data.common.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityImpl(private val context: Context) : Connectivity {
  
  override fun hasNetworkAccess(): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = connectivityManager.activeNetworkInfo
    return info != null && info.isConnected
  }
}