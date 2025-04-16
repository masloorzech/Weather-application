package com.example.weatherappjetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.data.managers.DataStoreManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreManager = remember { DataStoreManager(context) }

    var city by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var favoriteCities by remember { mutableStateOf<List<String>>(emptyList()) }

    val weatherState by viewModel.weather.collectAsState()

    val isFavorite = city.isNotEmpty() && favoriteCities.contains(city)
    val favouriteIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    LaunchedEffect(Unit) {
        dataStoreManager.favoriteCitiesFlow.collect { cities ->
            favoriteCities = cities
        }
    }

    LaunchedEffect(Unit) {
        dataStoreManager.lastCityFlow.collect { lastCity ->
            if (lastCity.isNotEmpty()) {
                city = lastCity
                viewModel.updateCity(lastCity)
                viewModel.fetchWeather()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Miasto") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    singleLine = true,
                    modifier = Modifier.menuAnchor()
                )

                if (favoriteCities.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        favoriteCities.forEach { favCity ->
                            DropdownMenuItem(
                                text = { Text(favCity) },
                                onClick = {
                                    city = favCity
                                    expanded = false
                                    viewModel.updateCity(city)
                                    viewModel.fetchWeather()
                                }
                            )
                        }
                    }
                }
            }

            IconButton(onClick = {
                if (city.isNotEmpty()) {
                    if (favoriteCities.contains(city)) {
                        scope.launch {
                            val updated = favoriteCities.filter { it != city }
                            dataStoreManager.saveFavoriteCities(updated)
                            favoriteCities = updated
                        }
                    } else {
                        scope.launch {
                            val updated = favoriteCities + city
                            dataStoreManager.saveFavoriteCities(updated)
                            favoriteCities = updated
                        }
                    }
                }
            }) {
                Icon(imageVector = favouriteIcon, contentDescription = "Toggle favorite")
            }

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

        Spacer(modifier = Modifier.height(16.dp))

        weatherState?.let { weather ->
            Text("🌍 Współrzędne: ${weather.coord.lat}, ${weather.coord.lon}")
            Text("🕒 Czas: ${formatUnixTime(weather.dt)}")
            Text("🌡️ Temp: ${weather.main.temp}°C")
            Text("💨 Ciśnienie: ${weather.main.pressure} hPa")
            Text("📖 Opis: ${weather.weather.firstOrNull()?.description ?: "Brak opisu"}")

            weather.weather.firstOrNull()?.icon?.let { icon ->
                Image(
                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${icon}@2x.png"),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
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

fun formatUnixTime(unixTime: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    val date = Date(unixTime * 1000)
    return sdf.format(date)
}