package com.example.weatherappjetpackcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.R
import com.example.weatherappjetpackcompose.ui.components.TemperaturePanel
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import kotlin.math.round
import com.example.weatherappjetpackcompose.ui.components.AdditionalInfoPanel

@Preview
@Composable
fun WeatherAdditionalInfoScreen(viewModel: WeatherViewModel = viewModel() ) {
    val weatherState = viewModel.weather.collectAsState().value

    val city by viewModel.city.collectAsState()

    var pixel_font = FontFamily(Font(R.font.pixel_sans))

    //Read unit form shared prefs
    var isMetersPerSecond by remember { mutableStateOf(true) }

    val windUnit = if (isMetersPerSecond) "m/s" else "km/h"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color= Color(0xFF181820))
            .padding(vertical = 32.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .weight(0.5f), contentAlignment = Alignment.Center
        ) {
            Text(
                "ClimaSynth",
                fontSize = 42.sp,
                color = Color(0xFFD2D1D3),
                fontFamily = pixel_font
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f))
        {
            weatherState?.let { weather ->
                AdditionalInfoPanel(
                    value = weather.wind.speed.toString(),
                    unit = windUnit.toString(),
                    imageRes = R.drawable.wind
                )
            } ?: run {
                AdditionalInfoPanel(
                    value = "No data",
                    unit = windUnit.toString(),
                    imageRes = R.drawable.wind
                )
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.1f)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f))
        {
            weatherState?.let { weather ->
                AdditionalInfoPanel(
                    value = weather.main.humidity.toString(),
                    unit = "%",
                    imageRes = R.drawable.humidity
                )
            } ?: run {
                AdditionalInfoPanel(
                    value = "No data",
                    unit = "%",
                    imageRes = R.drawable.humidity
                )
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.1f)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f))
        {
            weatherState?.let { weather ->
                AdditionalInfoPanel(
                    value = weather.visibility.toString(),
                    unit = "m",
                    imageRes = R.drawable.visibility
                )
            } ?: run {
                AdditionalInfoPanel(
                    value = "No data",
                    unit = "m",
                    imageRes = R.drawable.visibility
                )
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.5f)
        )
    }


}
