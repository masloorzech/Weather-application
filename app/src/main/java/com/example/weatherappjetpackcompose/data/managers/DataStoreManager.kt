
package com.example.weatherappjetpackcompose.data.managers

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "weather_prefs")

object DataStoreKeys {
    val FAVORITES = stringPreferencesKey("favorites")
    val LAST_CITY = stringPreferencesKey("last_city")
}

class DataStoreManager(private val context: Context) {
    val favoriteCitiesFlow: Flow<List<String>> = context.dataStore.data.map { prefs ->
        prefs[DataStoreKeys.FAVORITES]?.let {
            Json.decodeFromString<List<String>>(it)
        } ?: emptyList()
    }

    val lastCityFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[DataStoreKeys.LAST_CITY] ?: ""
    }

    suspend fun saveFavoriteCities(cities: List<String>) {
        context.dataStore.edit { prefs ->
            prefs[DataStoreKeys.FAVORITES] = Json.encodeToString(cities)
        }
    }

    suspend fun saveLastCity(city: String) {
        context.dataStore.edit { prefs ->
            prefs[DataStoreKeys.LAST_CITY] = city
        }
    }
}
