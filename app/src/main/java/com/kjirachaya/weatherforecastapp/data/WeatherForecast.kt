package com.kjirachaya.weatherforecastapp.data

data class WeatherForecast(
    val temp: Double,
    val main: String,
    val description: String,
    val iconUrl: String,
    val humidity: Double,
    val windSpeed: Double,
    val windDeg: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val name: String,
    val clouds: Double,
    val dt: String,
    val feelsLike: Double,
    val visibility: Long,
    val sunrise: String,
    val sunset: String,
)
