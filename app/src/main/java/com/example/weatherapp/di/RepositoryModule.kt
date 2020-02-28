package com.example.weatherapp.di

import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.data.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}