package com.cobeisfresh.template.di

import com.cobeisfresh.template.ui.weather.presentation.WeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
  viewModel { WeatherViewModel(get()) }
}