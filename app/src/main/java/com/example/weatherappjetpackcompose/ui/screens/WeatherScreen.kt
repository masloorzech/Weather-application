package com.example.weatherappjetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import coil.compose.rememberAsyncImagePainter
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.collections.get

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    var city by remember { mutableStateOf(TextFieldValue("Warszawa")) }

    // Fetch weather data when city changes
    LaunchedEffect(city.text) {
        viewModel.fetchWeather(city.text, "d2e17bf45858c86f284cd280a2dd1ee3")
    }

    val weatherState = viewModel.weather.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = city,
            onValueChange = { city = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        weatherState?.let { weather ->
            Text("🌡️ Temp: ${weather.main.temp}°C")
            Text("📍 Miejscowość: ${weather.name}")
            Text("🌀 Wiatr: ${weather.wind.speed} m/s")
            Text("📖 Opis: ${weather.weather[0].description}")

            Image(
                painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        } ?: run {
            Text("Ładowanie danych...")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}
