package  com.example.weatherapp.data

import com.example.weatherapp.domain.WeatherRepository
import retrofit2.Response


class WeatherRepositoryImpl(val api: WeatherApi) : WeatherRepository {

    override suspend fun getWeather(pincode: String, apiKey: String): Response<WeatherResponse> {
        return api.getCityWeather(pincode,apiKey)
    }

    override suspend fun getWeatherForecast(
        pincode: String,
        apiKey: String
    ): Response<WeatherForecastResponse> {
        return api.getWeatherForecast(pincode,apiKey)
    }
}