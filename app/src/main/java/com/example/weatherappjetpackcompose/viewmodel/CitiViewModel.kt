package com.example.weatherappjetpackcompose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappjetpackcompose.data.managers.SharedPreferencesHelper
import com.example.weatherappjetpackcompose.data.remote.CityResponse
import com.example.weatherappjetpackcompose.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.weatherappjetpackcompose.data.model.WeatherResponse
import com.example.weatherappjetpackcompose.data.model.ForecastResponse
import com.google.gson.Gson

class CitiViewModel(application: Application) : AndroidViewModel(application) {
  private val prefsHelper = SharedPreferencesHelper(application)

  private val _favouriteCities = MutableStateFlow<List<String>>(emptyList())
  val favouriteCities: StateFlow<List<String>> = _favouriteCities

  private val _autocompleteResults = MutableStateFlow<List<CityResponse>>(emptyList())
  val autocompleteResults: StateFlow<List<CityResponse>> = _autocompleteResults

  private val gson = Gson()

  private val _weatherDataMap = MutableStateFlow<Map<String, WeatherResponse>>(emptyMap())
  val weatherDataMap: StateFlow<Map<String, WeatherResponse>> = _weatherDataMap

  private val _forecastDataMap = MutableStateFlow<Map<String, ForecastResponse>>(emptyMap())
  val forecastDataMap: StateFlow<Map<String, ForecastResponse>> = _forecastDataMap

  fun searchCities(query: String) {
    viewModelScope.launch {
      try {
        val results = RetrofitClient.api.searchCities(
          cityName = query,
          apiKey = "d2e17bf45858c86f284cd280a2dd1ee3"
        )
        _autocompleteResults.value = results
      } catch (e: Exception) {
        _autocompleteResults.value = emptyList()
      }
    }
  }

  fun clearSuggestions() {
    _autocompleteResults.value = emptyList()
  }

  init {
    loadFavouriteCities()
  }

  fun loadFavouriteCities() {
    viewModelScope.launch {
      _favouriteCities.value = prefsHelper.getFavouriteCities()
    }
  }

  fun addCity(city: String){
    prefsHelper.addFavouriteCity(city)
    loadFavouriteCities()
    loadAllFavouriteWeatherData("d2e17bf45858c86f284cd280a2dd1ee3")
  }

  fun removeCity(city: String) {
    prefsHelper.removeFavouriteCity(city)
    loadFavouriteCities()
    loadAllFavouriteWeatherData("d2e17bf45858c86f284cd280a2dd1ee3")
  }

  fun loadAllFavouriteWeatherData(apiKey: String) {
    viewModelScope.launch {
      val cities = prefsHelper.getFavouriteCities()
      val weatherMap = mutableMapOf<String, WeatherResponse>()
      val forecastMap = mutableMapOf<String, ForecastResponse>()

      for (city in cities) {
        try {
          val weather = RetrofitClient.api.getCurrentWeather(city, apiKey)
          prefsHelper.saveWeatherDataForCity(city, gson.toJson(weather))
          weatherMap[city] = weather
        } catch (e: Exception) {
          prefsHelper.getWeatherDataForCity(city)?.let {
            try {
              val cached = gson.fromJson(it, WeatherResponse::class.java)
              weatherMap[city] = cached
            } catch (_: Exception) { }
          }
        }

        try {
          val forecast = RetrofitClient.api.getForecast(city, apiKey)
          prefsHelper.saveForecastDataForCity(city, gson.toJson(forecast))
          forecastMap[city] = forecast
        } catch (e: Exception) {
          prefsHelper.getForecastDataForCity(city)?.let {
            try {
              val cached = gson.fromJson(it, ForecastResponse::class.java)
              forecastMap[city] = cached
            } catch (_: Exception) { }
          }
        }
      }

      _weatherDataMap.value = weatherMap
      _forecastDataMap.value = forecastMap
    }
  }

}
