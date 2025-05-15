package com.example.weatherappjetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DescriptionPanel(
    description: String,
    pressure: String,
    imageRes: String?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFF2F2C37))
            .padding(8.dp)
    )
    {
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(2f)
        ){
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center

            ){
                Text("$description",
                    fontSize = 20.sp,
                    color = Color(0xFFD2D1D3))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f))
            {
                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Text("$pressure",
                        fontSize = 32.sp,
                        color = Color(0xFFD2D1D3))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.07f)
                )
                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Text("hPa",
                        fontSize = 32.sp,
                        color = Color(0xFFD2D1D3))
                }
            }

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
                .fillMaxHeight()
                .weight(2f),
            contentAlignment = Alignment.Center
        ){
            imageRes?.firstOrNull()?.let { imageRes
                Image(
                    painter = rememberAsyncImagePainter(imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }?: run{
                Text("No data",
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD2D1D3),
                    modifier = Modifier.fillMaxWidth())
            }

        }

    }
}
