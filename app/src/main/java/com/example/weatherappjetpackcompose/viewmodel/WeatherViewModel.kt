package com.example.weatherappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappjetpackcompose.data.model.ForecastItem
import com.example.weatherappjetpackcompose.data.model.ForecastResponse
import com.example.weatherappjetpackcompose.data.model.WeatherResponse
import com.example.weatherappjetpackcompose.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _forecast = MutableStateFlow<List<ForecastItem>>(emptyList())
    val forecast: StateFlow<List<ForecastItem>> = _forecast

    private val _city = MutableStateFlow("Warszawa")

    private val apiKey = "d2e17bf45858c86f284cd280a2dd1ee3"

    fun updateCity(newCity: String) {
        _city.value = newCity
        fetchWeather()
        fetchForecast()
    }

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getCurrentWeather(_city.value, apiKey)
                _weather.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchForecast() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.getForecast(_city.value, apiKey)
                _forecast.value = result.list.filterIndexed { index, _ -> (index+2) % 8 == 0 }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
