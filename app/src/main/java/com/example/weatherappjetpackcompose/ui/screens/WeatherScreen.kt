package com.example.weatherappjetpackcompose.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.MenuActivity
import com.example.weatherappjetpackcompose.data.managers.DataStoreManager
import com.example.weatherappjetpackcompose.ui.components.DescriptionPanel
import com.example.weatherappjetpackcompose.ui.components.SmallPanel
import com.example.weatherappjetpackcompose.ui.components.TemperaturePanel
import com.example.weatherappjetpackcompose.ui.components.TextField
import com.example.weatherappjetpackcompose.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreManager = remember { DataStoreManager(context) }
    var isCelsius by remember { mutableStateOf(true) }
    val temperatureUnitString = if (isCelsius) "°C" else "°F"
    var city by remember { mutableStateOf("Warsaw")}
    var expanded by remember { mutableStateOf(false) }
    var favoriteCities by remember { mutableStateOf<List<String>>(emptyList()) }

    val weatherState by viewModel.weather.collectAsState()

    var pixel_font = FontFamily(Font(R.font.pixel_sans))


    val isFavorite = city.isNotEmpty() && favoriteCities.contains(city)
    val favouriteIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    LaunchedEffect(true) {
        launch {
            dataStoreManager.favoriteCitiesFlow.collect { cities ->
                favoriteCities = cities
            }
        }
        launch {
            dataStoreManager.lastCityFlow.collect { lastCity ->
                if (lastCity.isNotEmpty()) {
                    city = lastCity
                    viewModel.updateCity(lastCity)
                    viewModel.fetchWeather()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color= Color(0xFF181820))
            .padding(vertical = 32.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .weight(0.5f), contentAlignment = Alignment.Center){
            Text("ClimaSynth",
                fontSize = 42.sp,
                color = Color(0xFFD2D1D3),
                fontFamily = pixel_font
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.4f))
        {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF2F2C37), shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(4f),
                contentAlignment = Alignment.Center
            ){
                Text("$city", color = Color(0xFFD2D1D3),
                    fontSize = 20.sp)
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                //Refresh button
                IconButton(onClick = {
                    if (city.isNotEmpty()) {
                        viewModel.updateCity(city)
                        viewModel.fetchWeather()
                        scope.launch { dataStoreManager.saveLastCity(city) }
                    }
                }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh data")
                }
            }
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
                val temp = if (isCelsius) weather.main.temp else (weather.main.temp * 9/5) + 32
                TemperaturePanel(
                    temperature = "${"%.1f".format(temp)}",
                    unit = temperatureUnitString,
                    latitude = weather.coord.lat.toString(),
                    longitude = weather.coord.lon.toString()
                )
            } ?: run {
                TemperaturePanel(
                    temperature = "?",
                    unit = temperatureUnitString,
                    latitude = "?",
                    longitude ="?"
                )
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        )
        Box(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .weight(1f))
        {
            weatherState?.let { weather ->
                DescriptionPanel(
                    description = weather.weather.firstOrNull()?.description ?: "No data",
                    pressure = weather.main.pressure.toString(),
                    imageRes = "https://openweathermap.org/img/wn/${weather.weather.firstOrNull()?.icon}@2x.png"
                )
            } ?: run {
                DescriptionPanel(
                    description = "?",
                    pressure = "?",
                    imageRes = null
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                weatherState?.let { weather ->
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }?: run{
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "?",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Box( modifier = Modifier
                .fillMaxWidth()
                .weight(2f)) {
                weatherState?.let { weather ->
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }?: run{
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "?",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Box( modifier = Modifier
                .fillMaxWidth()
                .weight(2f)) {
                weatherState?.let { weather ->
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }?: run{
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "?",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Box( modifier = Modifier
                .fillMaxWidth()
                .weight(2f)) {
                weatherState?.let { weather ->
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }?: run{
                    SmallPanel(
                        time = "6:00AM",
                        temperature = "?",
                        unit = temperatureUnitString,
                        imageRes = 0
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.1f)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.4f))
        {
            Box(
                Modifier.fillMaxWidth()
                    .fillMaxHeight()
                    .weight(2f)
            ){
                TextField(
                    text = "Fill me daddy"
                )
            }
            Spacer(
                Modifier.fillMaxWidth()
                    .weight(1.3f)
            )

            Box(Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp))
                .weight(1f)
                .clickable{
                    val intent = Intent(context, MenuActivity::class.java)
                    context.startActivity(intent)
                },
                contentAlignment = Alignment.Center,
                )
            {
                Image(painter = painterResource(R.drawable.slider),
                    contentDescription = "Slider",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.4f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen()
}

fun formatUnixTime(unixTime: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    val date = Date(unixTime * 1000)
    return sdf.format(date)
}