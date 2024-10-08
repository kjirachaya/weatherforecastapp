package com.kjirachaya.weatherforecastapp.data.di

import com.kjirachaya.weatherforecastapp.data.remote.WeatherForecastApi
import com.kjirachaya.weatherforecastapp.data.remote.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherForecastRepository(api: WeatherForecastApi): WeatherForecastRepository = WeatherForecastRepository(api)
}
