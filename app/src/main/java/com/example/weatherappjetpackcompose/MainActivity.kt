package com.example.weatherappjetpackcompose

import WeatherForecastScreen
import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherappjetpackcompose.ui.screens.WeatherAdditionalInfoScreen
import com.example.weatherappjetpackcompose.ui.screens.WeatherScreen
import com.example.weatherappjetpackcompose.ui.theme.WeatherAppJetpackComposeTheme
import com.example.weatherappjetpackcompose.utils.rememberDevicePosture
import com.example.weatherappjetpackcompose.utils.DevicePosture
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.weatherappjetpackcompose.data.managers.SharedPreferencesHelper
import com.example.weatherappjetpackcompose.data.managers.WeatherWorker
import com.example.weatherappjetpackcompose.ui.screens.CitySelectionScreen
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scheduleWeatherWorker(applicationContext)

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
    override fun onDestroy() {
        super.onDestroy()
        WorkManager.getInstance(applicationContext)
            .cancelUniqueWork("weather_update_worker")
    }

}

fun scheduleWeatherWorker(context: Context) {
    val prefsHelper = SharedPreferencesHelper(context)

    // Domyślne odświeżanie: 15 minut
    var refreshRateMinutes = 15.0

    prefsHelper.getRefreshTimeMinutes()?.toDoubleOrNull()?.let {
        refreshRateMinutes += it
    }

    prefsHelper.getRefreshTimeHours()?.toDoubleOrNull()?.let {
        refreshRateMinutes += it * 60
    }

    val workRequest = PeriodicWorkRequestBuilder<WeatherWorker>(
        refreshRateMinutes.toLong(), TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context.applicationContext).enqueueUniquePeriodicWork(
        "weather_update_worker",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}

@Composable
fun WeatherPagerScreen(devicePosture: DevicePosture) {
    var selectedCities by remember { mutableStateOf(false) }

    when (devicePosture) {
        DevicePosture.TABLET -> {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.weight(0.1f)
                    .background(color = Color(0xFF181820))) {
                    Box(Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp))
                        .weight(1f)
                        .clickable{
                            selectedCities = !selectedCities
                        },
                        contentAlignment = Alignment.Center,
                    )
                    {
                        Image(painter = painterResource(com.example.weatherappjetpackcompose.R.drawable.baseline_location_pin_24),
                            contentDescription = "Select city",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (selectedCities){
                        CitySelectionScreen()
                    }else {
                        AdditionalWeatherScreen()
                    }
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
