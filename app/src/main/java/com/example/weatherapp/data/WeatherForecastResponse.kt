package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse (
    @SerializedName("list") val list : List<WeatherList>
)
data class WeatherList (

    @SerializedName("dt") val dt : Int,
    @SerializedName("temp") val temp : Temp,
    @SerializedName("pressure") val pressure : Double,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Int,
    @SerializedName("clouds") val clouds : Int,
    @SerializedName("rain") val rain : Double
)

data class Temp (

    @SerializedName("day") val day : Double,
    @SerializedName("min") val min : Double,
    @SerializedName("max") val max : Double,
    @SerializedName("night") val night : Double,
    @SerializedName("eve") val eve : Double,
    @SerializedName("morn") val morn : Double
)
