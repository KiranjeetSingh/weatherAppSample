package com.example.weatherapp.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCityWeather(@Query("zip") query: String,
                            @Query("APPID") appId : String,@Query ("units") units:String="metric"): Response<WeatherResponse>


    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherForecast(@Query("zip") query: String,
                               @Query("APPID") appId : String,@Query("cnt")cnt:Int=7,@Query ("units") units:String="metric"): Response<WeatherForecastResponse>



}