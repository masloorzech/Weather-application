package com.example.weatherappjetpackcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TemperaturePanel(
    temperature: String,
    unit: String,
    latitude: String,
    longitude: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFF2F2C37), shape = RoundedCornerShape(8.dp))
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
                fontSize = 58.sp,
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
                    fontSize = 24.sp,
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
                    fontSize = 24.sp,
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
