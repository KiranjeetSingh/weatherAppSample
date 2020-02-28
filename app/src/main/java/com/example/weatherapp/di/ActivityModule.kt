package com.example.weatherapp.di

import com.example.weatherapp.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeWeatherActivity(): WeatherActivity
}