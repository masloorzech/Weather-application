package com.example.weatherappjetpackcompose.data.remote

import com.example.weatherappjetpackcompose.data.model.ForecastResponse
import com.example.weatherappjetpackcompose.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en"
    ): ForecastResponse

  @GET("https://api.openweathermap.org/geo/1.0/direct")
  suspend fun searchCities(
    @Query("q") cityName: String,
    @Query("limit") limit: Int = 5,
    @Query("appid") apiKey: String
  ): List<CityResponse>
}
