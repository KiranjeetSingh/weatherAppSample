package com.example.weatherapp.di

import com.example.weatherapp.domain.WeatherUseCase
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides

@Module/*(includes = [ViewModelModule::class, ApiModule::class])*/
class UseCaseModule {

    @Provides
    fun provideWeatherUseCaseImpl(repository: WeatherRepository): WeatherUseCase =
        WeatherUseCaseImpl(repository)

}