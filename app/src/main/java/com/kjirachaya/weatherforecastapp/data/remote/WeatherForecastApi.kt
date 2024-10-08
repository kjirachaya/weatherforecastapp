package com.kjirachaya.weatherforecastapp.data.remote

import com.kjirachaya.weatherforecastapp.data.model.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
    ): WeatherForecastResponse
}
