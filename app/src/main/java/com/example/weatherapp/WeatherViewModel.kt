package com.example.weatherapp

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.Weather
import com.example.weatherapp.data.WeatherForecastResponse
import com.example.weatherapp.data.WeatherList
import com.example.weatherapp.data.WeatherResponse
import com.example.weatherapp.domain.WeatherUseCase
import kotlinx.coroutines.*
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    var weatherListData: MutableLiveData<List<WeatherList>> = MutableLiveData()
    var errorData: MutableLiveData<String> = MutableLiveData()
    var error: ObservableBoolean = ObservableBoolean()
    var showLoader: ObservableBoolean = ObservableBoolean(true)
    var cityName:ObservableField<String> = ObservableField()
    var humidity:ObservableField<String> = ObservableField()
    var description:ObservableField<String> = ObservableField()
    var temperature:ObservableField<String> = ObservableField()
    var windSpeed:ObservableField<String> = ObservableField()
    var showForecastLoader: ObservableBoolean = ObservableBoolean(false)
    var searchZipCode = ""
    val exceptionHandler = CoroutineExceptionHandler { _, t ->
        showLoader.set(false)
        showForecastLoader.set(false)
        if(t is UnknownHostException){
            errorData.postValue( "No Internet Connection")
        }
    }

    val viewModelScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)

    fun getWeather(zipcode:String="110030") {
        searchZipCode = zipcode
        showLoader.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = weatherUseCase.getWeather(zipcode.plus(",IN"),Constants.apiKey)
            withContext(Dispatchers.Main) {
                showLoader.set(false)
                if (result.isSuccessful()) {
                    error.set(false)
                    val response: WeatherResponse? = result.body()
                    cityName.set(response?.name)
                    humidity.set(response?.main?.humidity.toString())
                    windSpeed.set(response?.wind?.speed.toString().plus(" m/s"))
                    description.set(response?.weather?.get(0)?.main)
                    temperature.set(response?.main?.temp?.toString())
                } else {
                    errorData.value = result.message()
                }
            }
        }
    }

    fun onSearchActionPerformed(rawUserId: String){
        getWeather(rawUserId)
    }

    fun onReloadClick() {
        error.set(false)
        getWeather(searchZipCode)
    }

    fun getWeatherForecast(){
        showForecastLoader.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = weatherUseCase.getWeatherForecast(searchZipCode.plus(",IN"),Constants.apiKey)
            withContext(Dispatchers.Main) {
                showForecastLoader.set(false)
                if (result.isSuccessful()) {
                    error.set(false)
                    val response: WeatherForecastResponse? = result.body()
                    weatherListData.value = response?.list
                } else {
                    errorData.value = result.message()
                }
            }
        }
    }
}