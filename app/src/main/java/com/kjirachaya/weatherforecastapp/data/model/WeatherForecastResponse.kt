package com.kjirachaya.weatherforecastapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    val main: Main,
    val weather: List<WeatherDetails>,
    val wind: Wind,
    val name: String,
    val clouds: Cloud,
    val dt: Long,
    val timezone: Long,
    val visibility: Long,
    val sys: Sys,
)

data class Main(
    val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Double,
    val humidity: Double,
    @SerializedName("feels_like") val feelsLike: Double,
)

data class WeatherDetails(
    val main: String,
    val description: String,
    val icon: String,
)

data class Wind(
    val speed: Double,
    val deg: Double,
)

data class Cloud(
    val all: Double,
)

data class Sys(
    val sunrise: Long,
    val sunset: Long,
)
