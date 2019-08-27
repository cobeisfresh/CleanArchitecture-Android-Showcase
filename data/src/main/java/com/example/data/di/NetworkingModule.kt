package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.networking.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
const val API_KEY = "1ca34d622e60278cc8a62d2f53f2c982"

val networkingModule = module {
  single { GsonConverterFactory.create() as Converter.Factory }
  single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }
  single {
    OkHttpClient.Builder().apply {
      if (BuildConfig.DEBUG) addInterceptor(get())
          .callTimeout(10, TimeUnit.SECONDS)
    }.build()
  }
  single {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(get())
        .addConverterFactory(get())
        .build()
  }
  single { get<Retrofit>().create(WeatherApi::class.java) }
}