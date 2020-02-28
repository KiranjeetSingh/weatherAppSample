package com.example.weatherapp.domain

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {
    override suspend fun getWeather(pincode: String, apiKey: String)=
        weatherRepository.getWeather(pincode,apiKey)

    override suspend fun getWeatherForecast(
        pincode: String,
        apiKey: String
    ) = weatherRepository.getWeatherForecast(pincode,apiKey)

}