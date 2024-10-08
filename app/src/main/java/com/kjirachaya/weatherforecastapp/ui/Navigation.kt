package com.kjirachaya.weatherforecastapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kjirachaya.weatherforecastapp.ui.weather.WeatherForecastScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { WeatherForecastScreen() }
    }
}