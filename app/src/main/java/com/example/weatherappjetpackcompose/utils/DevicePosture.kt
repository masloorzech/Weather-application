package com.example.weatherappjetpackcompose.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi

enum class DevicePosture {
    PHONE_PORTRAIT,
    PHONE_LANDSCAPE,
    TABLET
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberDevicePosture(activity: ComponentActivity): DevicePosture {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val orientation = configuration.orientation

    return when {
        screenWidthDp >= 960 && orientation == Configuration.ORIENTATION_LANDSCAPE ||  screenWidthDp >= 600 && orientation == Configuration.ORIENTATION_PORTRAIT-> DevicePosture.TABLET
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE -> DevicePosture.PHONE_LANDSCAPE
        else -> DevicePosture.PHONE_PORTRAIT
    }
}
