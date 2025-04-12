package com.example.weatherappjetpackcompose.data.model

data class WeatherResponse(
    val name: String,
    val coord: Coord,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val visibility: Int
)

data class Coord(val lon: Double, val lat: Double)
data class Main(val temp: Float, val pressure: Int, val humidity: Int)
data class Weather(val description: String, val icon: String)
data class Wind(val speed: Float, val deg: Int)
