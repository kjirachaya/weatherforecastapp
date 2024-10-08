package com.kjirachaya.weatherforecastapp.data.remote

import com.kjirachaya.weatherforecastapp.data.model.WeatherForecastResponse

class WeatherForecastRepository(
    private val api: WeatherForecastApi,
) {
    suspend fun getWeather(city: String): WeatherForecastResponse = api.getWeather(city)
}
