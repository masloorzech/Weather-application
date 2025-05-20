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

class CitiViewModel(application: Application) : AndroidViewModel(application) {
  private val prefsHelper = SharedPreferencesHelper(application)

  private val _favouriteCities = MutableStateFlow<List<String>>(emptyList())
  val favouriteCities: StateFlow<List<String>> = _favouriteCities


  private val _autocompleteResults = MutableStateFlow<List<CityResponse>>(emptyList())
  val autocompleteResults: StateFlow<List<CityResponse>> = _autocompleteResults

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
  }

  fun removeCity(city: String) {
    prefsHelper.removeFavouriteCity(city)
    loadFavouriteCities()
  }
}
