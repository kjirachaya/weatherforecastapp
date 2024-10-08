package com.kjirachaya.weatherforecastapp.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kjirachaya.weatherforecastapp.R
import com.kjirachaya.weatherforecastapp.data.WeatherForecast
import com.kjirachaya.weatherforecastapp.ui.theme.WeatherForecastAppTheme

@Composable
fun WeatherForecastScreen(viewModel: WeatherForecastViewModel = hiltViewModel()) {
    val weatherForecastState by viewModel.weatherForecastState.collectAsState()

    WeatherForecastContent(
        weatherForecastState = weatherForecastState,
        onFetchWeather = viewModel::onForecastClick,
    )
}

@Composable
private fun WeatherForecastContent(
    weatherForecastState: WeatherForecastState,
    onFetchWeather: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary,
                            ),
                    ),
                ).padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_weather_header),
            contentDescription = null,
            modifier =
                Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(16.dp))
        WeatherForecastContentState(weatherForecastState)
        Spacer(modifier = Modifier.weight(1f))
        WeatherForecastInputView(onFetchWeather)
    }
}

@Composable
private fun WeatherForecastContentState(weatherForecastState: WeatherForecastState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (weatherForecastState) {
            is WeatherForecastState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            is WeatherForecastState.Success -> {
                WeatherForecastDetail(weather = weatherForecastState.weatherForecast)
            }

            is WeatherForecastState.Error -> {
                WeatherForecastErrorState(weatherForecastState)
            }

            else -> Unit
        }
    }
}

@Composable
private fun WeatherForecastInputView(onFetchWeather: (String) -> Unit) {
    var city by remember { mutableStateOf("") }
    var enabledButton by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Column {
        Text(
            text = stringResource(R.string.get_weather_forecast_by_city_name),
            style =
                MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            colors =
                OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                ),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
            value = city,
            onValueChange = { text ->
                city = text.trimIndent().filter { it.isLetterOrDigit() || it.isWhitespace() }
                enabledButton = city.isNotEmpty()
            },
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_city),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            shape = RoundedCornerShape(size = 18.dp),
            keyboardActions =
                KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    if (enabledButton) {
                        onFetchWeather(city)
                    }
                }),
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                ),
            trailingIcon = {
                Button(
                    enabled = enabledButton,
                    onClick = {
                        focusManager.clearFocus()
                        onFetchWeather(city)
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier =
                        Modifier
                            .padding(end = 12.dp)
                            .height(40.dp)
                            .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(25.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                        ),
                    elevation = ButtonDefaults.buttonElevation(4.dp),
                ) {
                    Text(
                        text = stringResource(R.string.forecast),
                        style =
                            MaterialTheme.typography.bodyLarge.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
private fun WeatherForecastErrorState(weatherForecastState: WeatherForecastState.Error) {
    Column(
        modifier = Modifier.padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.error),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.error, weatherForecastState.message),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun WeatherForecastDetail(weather: WeatherForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = weather.dt,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = weather.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Card(
                modifier =
                    Modifier
                        .height(100.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(16.dp),
            ) {
                AsyncImage(
                    model = weather.iconUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(100.dp),
                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            Column {
                Text(
                    text = stringResource(R.string.temperature_celsius, weather.temp),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = weather.main,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = weather.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier =
                Modifier
                    .defaultMinSize(minHeight = 200.dp)
                    .fillMaxHeight(0.5f)
                    .verticalScroll(rememberScrollState()),
        ) {
            val feelsLike = stringResource(R.string.temperature_celsius, weather.feelsLike)
            WeatherForecastDetailCardView(
                title = stringResource(R.string.feels_like),
                value = feelsLike,
            )
            WeatherForecastDetailCardView(
                title = stringResource(R.string.humidity),
                value = "${weather.humidity}",
            )
            WeatherForecastDetailCardView(
                title = stringResource(R.string.wind_speed_deg),
                value = "${weather.windSpeed}/${weather.windDeg}",
            )
            WeatherForecastDetailCardView(
                title = stringResource(R.string.pressure),
                value = "${weather.pressure}",
            )
            WeatherForecastDetailCardView(
                title = stringResource(R.string.clouds),
                value = "${weather.clouds}",
            )
            val visibility = weather.visibility / 1000
            WeatherForecastDetailCardView(
                title = stringResource(R.string.visibility),
                value = stringResource(R.string.visibility_km, visibility),
            )
            val tempMin =
                stringResource(R.string.temperature_celsius, weather.tempMin)
            val tempMax =
                stringResource(R.string.temperature_celsius, weather.tempMax)
            WeatherForecastDetailCardView(
                title = stringResource(R.string.temperature_min_max),
                value = "$tempMin/$tempMax",
            )
            WeatherForecastDetailCardView(
                title = "Sunrise/Sunset",
                value = "${weather.sunrise}/${weather.sunset}",
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun WeatherForecastDetailCardView(
    title: String,
    value: String,
) {
    Card(
        modifier =
            Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(50.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = title, color = Color.Black, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = value, color = Color.Black, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(device = Devices.PIXEL_7A)
@Preview(device = Devices.PIXEL_3)
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_2)
@Composable
private fun Preview(
    @PreviewParameter(StatePreviewParameters::class) state: WeatherForecastState,
) {
    WeatherForecastAppTheme {
        WeatherForecastContent(
            weatherForecastState = state,
            onFetchWeather = {},
        )
    }
}

private class StatePreviewParameters : PreviewParameterProvider<WeatherForecastState> {
    override val values: Sequence<WeatherForecastState>
        get() =
            sequenceOf(
                WeatherForecastState.Idle,
                WeatherForecastState.Loading,
                WeatherForecastState.Success(
                    WeatherForecast(
                        temp = 20.0,
                        main = "Drizzle",
                        description = "light intensity drizzle",
                        iconUrl = "",
                        humidity = 81.0,
                        windSpeed = 4.1,
                        windDeg = 80.0,
                        tempMin = 279.15,
                        tempMax = 281.15,
                        pressure = 20.0,
                        name = "London",
                        clouds = 90.0,
                        dt = "1st Jun 2000, 20:20",
                        feelsLike = 90.0,
                        visibility = 10000L,
                        sunrise = "00:00",
                        sunset = "00:00",
                    ),
                ),
                WeatherForecastState.Error(
                    message = "Failed to fetch weather",
                ),
            )
}
