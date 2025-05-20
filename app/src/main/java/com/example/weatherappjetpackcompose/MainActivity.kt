package com.example.weatherappjetpackcompose

import WeatherForecastScreen
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import com.example.weatherappjetpackcompose.ui.screens.WeatherAdditionalInfoScreen
import com.example.weatherappjetpackcompose.ui.screens.WeatherScreen
import com.example.weatherappjetpackcompose.ui.theme.WeatherAppJetpackComposeTheme
import com.example.weatherappjetpackcompose.utils.rememberDevicePosture
import com.example.weatherappjetpackcompose.utils.DevicePosture
import android.content.pm.ActivityInfo
import com.example.weatherappjetpackcompose.ui.screens.CitySelectionScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            WeatherAppJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val devicePosture = rememberDevicePosture(this)
                    when (devicePosture){
                        DevicePosture.TABLET -> {
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        else -> {
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                }
                    WeatherPagerScreen(devicePosture)
                }
            }
        }
    }
}

@Composable
fun WeatherPagerScreen(devicePosture: DevicePosture) {
    when (devicePosture) {
        DevicePosture.TABLET -> {
            androidx.compose.foundation.layout.Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    AdditionalWeatherScreen()
                }
                Box(modifier = Modifier.weight(1f)) {
                    BasicWeatherScreen()
                }
                Box(modifier = Modifier.weight(1f)) {
                    ForecastWeatherScreen()
                }
            }
        }
        else -> {
            val pagerState = rememberPagerState(initialPage = 1, pageCount = { 4 })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .background(color= Color(0xFF181820))
                        .fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> AdditionalWeatherScreen()
                        1 -> BasicWeatherScreen()
                        2 -> ForecastWeatherScreen()
                        3 -> CitySelectionScreen()
                    }
                }
            }
        }
    }

@Composable
fun BasicWeatherScreen() {
    WeatherScreen()
}

@Composable
fun AdditionalWeatherScreen() {
    WeatherAdditionalInfoScreen()
}

@Composable
fun ForecastWeatherScreen() {
    WeatherForecastScreen()
}

@Composable
fun City() {
    CitySelectionScreen()
}
