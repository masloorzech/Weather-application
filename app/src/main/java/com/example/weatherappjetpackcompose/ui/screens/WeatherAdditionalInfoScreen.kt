package com.example.weatherappjetpackcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import kotlin.math.round

@Preview
@Composable
fun WeatherAdditionalInfoScreen(viewModel: WeatherViewModel = viewModel() ) {
    val weatherState = viewModel.weather.collectAsState().value
    var isMetersPerSecond by remember { mutableStateOf(true) }
    val windUnit = if (isMetersPerSecond) "m/s" else "km/h"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        weatherState?.let { weather ->
            val windSpeed = if (isMetersPerSecond) weather.wind.speed else (round(weather.wind.speed * 3.6))
            Text("🌬️ Wind: $windSpeed ${windUnit}, Direction : ${weather.wind.deg}°")
            Spacer(modifier = Modifier.height(16.dp))

            Text("💧 Humidity: ${weather.main.humidity}%")
            Spacer(modifier = Modifier.height(16.dp))

            Text("👀 Visibility: ${weather.visibility} m")
        } ?: run {
            Text("No data.")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Wind units:",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = isMetersPerSecond,
                    onCheckedChange = { isMetersPerSecond = it },
                    modifier = Modifier.size(60.dp, 40.dp)
                )

                Text(
                    text = if (isMetersPerSecond) "m/s" else "km/h",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


}
