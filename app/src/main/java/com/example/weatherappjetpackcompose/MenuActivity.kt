package com.example.weatherappjetpackcompose

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherappjetpackcompose.ui.screens.WeatherMenuScreen
import com.example.weatherappjetpackcompose.utils.DevicePosture
import com.example.weatherappjetpackcompose.utils.rememberDevicePosture

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                val devicePosture = rememberDevicePosture(this)
                when (devicePosture) {
                    DevicePosture.TABLET -> {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }
                    else -> {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                }
                WeatherMenuScreen()
            }
        }
    }
}