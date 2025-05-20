package com.example.weatherappjetpackcompose.data.managers

import android.R
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesHelper(context: Context) {
  private val sharedPreferences: SharedPreferences =
    context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)

  fun getFavouriteCities(): List<String> {
    return sharedPreferences.getStringSet("favourite_cities", emptySet())?.toList() ?: emptyList()
  }

  fun addFavouriteCity(city: String) {
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet()
      ?: mutableSetOf()
    cities.add(city)
    sharedPreferences.edit() { putStringSet("favourite_cities", cities) }
  }

  fun removeFavouriteCity(city: String) {
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet()
      ?: mutableSetOf()
    cities.remove(city)
    sharedPreferences.edit() { putStringSet("favourite_cities", cities) }
    sharedPreferences.edit() { remove("weather_$city") }

  }

  fun setSelectedCity(city: String) {
    sharedPreferences.edit() { putString("selected_city", city) }
  }

  fun getSelectedCity(): String? {
    return sharedPreferences.getString("selected_city", null)
  }

  fun saveWeatherDataForCity(city: String, weatherJson: String) {
    sharedPreferences.edit() { putString("weather_$city", weatherJson) }
  }

  fun getWeatherDataForCity(city: String): String? {
    return sharedPreferences.getString("weather_$city", null)
  }

  fun saveForecastDataForCity(city: String, forecastJson: String) {
    sharedPreferences.edit() { putString("forecast_$city", forecastJson) }
  }

  fun getForecastDataForCity(city: String): String? {
    return sharedPreferences.getString("forecast_$city", null)
  }

  fun saveTempUnit(isCelcius: Boolean) {
    sharedPreferences.edit() { putString("temp_unit", isCelcius.toString()) }
  }

  fun getTempUnit(): String? {
    return sharedPreferences.getString("temp_unit", null)
  }

  fun saveWindUnit(isMS: Boolean) {
    sharedPreferences.edit() { putString("wind_unit", isMS.toString()) }
  }

  fun getWindUnit(): String? {
    return sharedPreferences.getString("wind_unit", null)
  }

  fun saveRefreshTimeMinutes(time: String) {
    sharedPreferences.edit() { putString("refresh_time_minutes", time) }
  }

  fun getRefreshTimeMinutes(): String? {
    return sharedPreferences.getString("refresh_time_minutes", "15")
  }


  fun saveRefreshTimeHours(time: String) {
    sharedPreferences.edit() { putString("refresh_time_hours", time) }
  }

  fun getRefreshTimeHours(): String? {
    return sharedPreferences.getString("refresh_time_hours", "0")
  }

}

