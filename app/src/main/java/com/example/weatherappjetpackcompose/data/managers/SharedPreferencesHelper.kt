package com.example.weatherappjetpackcompose.data.managers

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
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    cities.add(city)
    sharedPreferences.edit() { putStringSet("favourite_cities", cities) }
  }

  fun removeFavouriteCity(city: String) {
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    cities.remove(city)
    sharedPreferences.edit() { putStringSet("favourite_cities", cities) }
    sharedPreferences.edit() { remove("weather_$city") }

  }

  fun setSelectedCity(city: String) {
    sharedPreferences.edit().putString("selected_city", city).apply()
  }

  fun getSelectedCity(): String? {
    return sharedPreferences.getString("selected_city", null)
  }

  fun saveWeatherDataForCity(city: String, weatherJson: String) {
    sharedPreferences.edit().putString("weather_$city", weatherJson).apply()
  }

  fun getWeatherDataForCity(city: String): String? {
    return sharedPreferences.getString("weather_$city", null)
  }
}
