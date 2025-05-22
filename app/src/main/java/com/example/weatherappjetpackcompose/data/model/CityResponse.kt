package com.example.weatherappjetpackcompose.data.remote

data class CityResponse(
  val name: String,
  val country: String,
  val state: String? = null,
  val lat: Double,
  val lon: Double
)
