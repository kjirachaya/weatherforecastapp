package com.kjirachaya.weatherforecastapp.ui.weather

import androidx.lifecycle.ViewModel
import com.kjirachaya.weatherforecastapp.data.WeatherForecast
import com.kjirachaya.weatherforecastapp.data.di.AppCoroutineScope
import com.kjirachaya.weatherforecastapp.data.remote.WeatherForecastRepository
import com.kjirachaya.weatherforecastapp.utils.formatTimestampToDateTimeString
import com.kjirachaya.weatherforecastapp.utils.formatTimestampToTimeString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel
    @Inject
    constructor(
        private val repository: WeatherForecastRepository,
        @AppCoroutineScope private val coroutineScope: CoroutineScope,
    ) : ViewModel() {
        private val _weatherForecastState =
            MutableStateFlow<WeatherForecastState>(WeatherForecastState.Idle)
        val weatherForecastState: StateFlow<WeatherForecastState> = _weatherForecastState

        fun onForecastClick(city: String) {
            coroutineScope.launch {
                _weatherForecastState.value = WeatherForecastState.Loading
                try {
                    val response = repository.getWeather(city)
                    val weather = response.weather.firstOrNull()
                    val wind = response.wind
                    val main = response.main
                    val sys = response.sys
                    val iconUrl = "https://openweathermap.org/img/wn/${weather?.icon}@2x.png"
                    _weatherForecastState.value =
                        WeatherForecastState.Success(
                            WeatherForecast(
                                dt = response.dt.formatTimestampToDateTimeString(response.timezone.toInt()),
                                temp = main.temp,
                                description = weather?.description.orEmpty(),
                                iconUrl = iconUrl,
                                main = weather?.main.orEmpty(),
                                humidity = main.humidity,
                                windSpeed = wind.speed,
                                windDeg = wind.deg,
                                tempMax = main.tempMax,
                                tempMin = main.tempMin,
                                pressure = main.pressure,
                                name = response.name,
                                clouds = response.clouds.all,
                                feelsLike = response.main.feelsLike,
                                visibility = response.visibility,
                                sunset = sys.sunset.formatTimestampToTimeString(response.timezone.toInt()),
                                sunrise = sys.sunrise.formatTimestampToTimeString(response.timezone.toInt()),
                            ),
                        )
                } catch (e: Exception) {
                    _weatherForecastState.value =
                        WeatherForecastState.Error("Failed to fetch weather\n(${e.message})")
                }
            }
        }
    }

sealed class WeatherForecastState {
    data object Idle : WeatherForecastState()

    data object Loading : WeatherForecastState()

    data class Success(
        val weatherForecast: WeatherForecast,
    ) : WeatherForecastState()

    data class Error(
        val message: String,
    ) : WeatherForecastState()
}
