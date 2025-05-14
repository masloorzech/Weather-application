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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionPanel(
    description: String,
    pressure: Long,
    imageRes: Int,
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
                    fontSize = 10.sp,
                    color = Color(0xFFD2D1D3))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f))
            {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Text("$pressure",
                        fontSize = 10.sp,
                        color = Color(0xFFD2D1D3))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.07f)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(color = Color(0xFF4C4857),shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center

                ){
                    Text("hPa",
                        fontSize = 10.sp,
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
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                )
        }

    }
}
