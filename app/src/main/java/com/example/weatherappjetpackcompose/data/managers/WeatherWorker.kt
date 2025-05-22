package com.example.weatherappjetpackcompose.data.managers
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weatherappjetpackcompose.data.remote.RetrofitClient
import com.google.gson.Gson

class WeatherWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val prefs = SharedPreferencesHelper(appContext)
    private val gson = Gson()
    private val apiKey = "d2e17bf45858c86f284cd280a2dd1ee3"

    override suspend fun doWork(): Result {
        val city = prefs.getSelectedCity() ?: return Result.failure()

        try {
            val weatherResult = RetrofitClient.api.getCurrentWeather(city, apiKey)
            val weatherJson = gson.toJson(weatherResult)
            prefs.saveWeatherDataForCity(city, weatherJson)

            val forecastResult = RetrofitClient.api.getForecast(city, apiKey)
            val forecastJson = gson.toJson(forecastResult)
            prefs.saveForecastDataForCity(city, forecastJson)

            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.retry()
        }
    }
}
