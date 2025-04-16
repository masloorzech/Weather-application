package com.example.weatherappjetpackcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel

@Composable
fun WeatherAdditionalInfoScreen(viewModel: WeatherViewModel = viewModel() ) {
    val weatherState = viewModel.weather.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        weatherState?.let { weather ->
            Text("🌬️ Wiatr: ${weather.wind.speed} m/s, kierunek: ${weather.wind.deg}°")
            Text("💧 Wilgotność: ${weather.main.humidity}%")
            Text("👀 Widoczność: ${weather.visibility} m")
        } ?: run {
            Text("Brak danych.")
        }
    }
}