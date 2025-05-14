package com.example.weatherappjetpackcompose.ui.components

import android.annotation.SuppressLint
import com.example.weatherappjetpackcompose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.text.font.FontStyle
import com.example.weatherappjetpackcompose.MenuActivity


@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun TemperaturePanelPreview() {
    TemperaturePanel(
        temperature = "13",
        unit = "°C",
        latitude = 0.11f,
        longitude = 0.11f
    )
}

@Composable
fun TemperaturePanel(
    temperature: String,
    unit: String,
    latitude: Float,
    longitude: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFF2F2C37))
            .padding(8.dp)
    )
    {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(2f),
            contentAlignment = Alignment.Center
        ){
            Text("$temperature$unit",
                fontSize = 28.sp,
                color = Color(0xFFD2D1D3))
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
        )
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center

            ){
                Text("$latitude",
                    fontSize = 8.sp,
                    color = Color(0xFFD2D1D3))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
            )
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center

            ){
                Text("$longitude",
                    fontSize = 8.sp,
                    color = Color(0xFFD2D1D3))
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

    }
}
