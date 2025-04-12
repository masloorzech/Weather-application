package com.example.weatherappjetpackcompose

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherappjetpackcompose.ui.theme.WeatherAppJetpackComposeTheme

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
                    WeatherPagerScreen()
                }
            }
        }
    }
}

@Composable
fun WeatherPagerScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> BasicWeatherScreen()
            1 -> AdditionalWeatherScreen()
            2 -> ForecastWeatherScreen()
        }
    }
}

@Composable
fun BasicWeatherScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("🌤️ Podstawowe dane pogodowe", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun AdditionalWeatherScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("💨 Dodatkowe dane: wiatr, wilgotność, widoczność", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ForecastWeatherScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("📅 Prognoza na kolejne dni", style = MaterialTheme.typography.headlineMedium)
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppJetpackComposeTheme {
        Greeting("Android")
    }
}