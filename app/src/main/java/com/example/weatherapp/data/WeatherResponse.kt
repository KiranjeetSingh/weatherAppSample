package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName



data class WeatherResponse (

    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("main") val main : Main,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("name") val name : String
)

    data class Wind (

        @SerializedName("speed") val speed : Double,
        @SerializedName("deg") val deg : Int
    )
data class Main (

    @SerializedName("temp") val temp : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double
)

data class Weather (
    @SerializedName("id") var id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)