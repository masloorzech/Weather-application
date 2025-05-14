package com.example.weatherappjetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappjetpackcompose.R

@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun DescriptionPanelPreview() {
    Row(
        Modifier.fillMaxSize()
            .background(color = Color(0xFF181820))
    ){
     Box(
         modifier = Modifier
             .fillMaxHeight()
             .fillMaxWidth()
             .weight(1f)
     ){
         SmallPanel(
             time = "6:00AM",
             temperature = "12",
             unit = "°C",
             imageRes = R.drawable.slider
         )
     }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(1f)
        ){
            SmallPanel(
                time = "6:00AM",
                temperature = "12",
                unit = "°C",
                imageRes = R.drawable.slider
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(1f)
        ){
            SmallPanel(
                time = "6:00AM",
                temperature = "12",
                unit = "°C",
                imageRes = R.drawable.slider
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(1f)
        ){
            SmallPanel(
                time = "6:00AM",
                temperature = "12",
                unit = "°C",
                imageRes = R.drawable.slider
            )
        }
    }
}

@Composable
fun SmallPanel(
    time: String,
    temperature: String,
    unit: String,
    imageRes: Int
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp)
            .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ){
            Text("$time",
                color = Color(0xFFD2D1D3),
                fontSize = 8.sp)
        }
        Box(
            modifier = Modifier
                .background(color = Color(0xFF645F73), shape = RoundedCornerShape(8.dp))
                .aspectRatio(1f)
                .weight(2f),
            contentAlignment = Alignment.Center
        ){
            Text("$temperature$unit",
                color = Color(0xFFD2D1D3),
                fontSize = 12.sp)
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1f)
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