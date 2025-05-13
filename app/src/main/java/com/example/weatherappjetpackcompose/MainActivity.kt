package com.example.weatherappjetpackcompose

import WeatherForecastScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import com.example.weatherappjetpackcompose.ui.screens.WeatherAdditionalInfoScreen
import com.example.weatherappjetpackcompose.ui.screens.WeatherScreen
import com.example.weatherappjetpackcompose.ui.theme.WeatherAppJetpackComposeTheme
import com.example.weatherappjetpackcompose.utils.rememberDevicePosture
import com.example.weatherappjetpackcompose.utils.DevicePosture


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val devicePosture = rememberDevicePosture(this)
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
                    BasicWeatherScreen()
                }
                Box(modifier = Modifier.weight(1f)) {
                    AdditionalWeatherScreen()
                }
                Box(modifier = Modifier.weight(1f)) {
                    ForecastWeatherScreen()
                }
            }
        }
        else -> {
            val pagerState = rememberPagerState(pageCount = { 3 })

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomMenu()
                },
                floatingActionButton = {
                    MenuButton()
                },
                floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> BasicWeatherScreen()
                        1 -> AdditionalWeatherScreen()
                        2 -> ForecastWeatherScreen()
                    }
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
fun MenuButton() {
    val context = LocalContext.current
    androidx.compose.material3.FloatingActionButton(onClick = {
        val intent = Intent(context, MenuActivity::class.java)
        context.startActivity(intent)
    }) {
        Text("Menu")
    }
}

@Composable
fun BottomMenu() {
    androidx.compose.material3.BottomAppBar(
        actions = {
        }
    )
}