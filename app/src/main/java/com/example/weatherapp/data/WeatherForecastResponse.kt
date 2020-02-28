package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("list") val list: List<WeatherList>
)

data class WeatherList(
    @SerializedName("main") val temp: Temp,
    @SerializedName("weather") val weather: List<Weather>
)

data class Temp(
    @SerializedName("temp") val day: Double,
    @SerializedName("temp_min") val min: Double,
    @SerializedName("temp_max") val max: Double,
    @SerializedName("humidity") val night: Int
)


