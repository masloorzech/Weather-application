package com.example.weatherappjetpackcompose.data.model

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)