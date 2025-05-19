package com.example.weatherappjetpackcompose.data.managers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
  private val sharedPreferences: SharedPreferences =
    context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)

  fun getFavouriteCities(): List<String> {
    return sharedPreferences.getStringSet("favourite_cities", emptySet())?.toList() ?: emptyList()
  }

  fun addFavouriteCity(city: String) {
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    cities.add(city)
    sharedPreferences.edit().putStringSet("favourite_cities", cities).apply()
  }

  fun removeFavouriteCity(city: String) {
    val cities = sharedPreferences.getStringSet("favourite_cities", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    cities.remove(city)
    sharedPreferences.edit().putStringSet("favourite_cities", cities).apply()
  }
}
