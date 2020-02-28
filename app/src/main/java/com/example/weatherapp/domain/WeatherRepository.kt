package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherForecastResponse
import com.example.weatherapp.data.WeatherResponse
import retrofit2.Response


interface WeatherRepository  {
    suspend fun getWeather(pincode:String,apiKey:String): Response<WeatherResponse>

    suspend fun getWeatherForecast(pincode:String,apiKey:String): Response<WeatherForecastResponse>
}